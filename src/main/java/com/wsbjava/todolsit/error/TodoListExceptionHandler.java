package com.wsbjava.todolsit.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TodoListExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    TodoListApiException handleConstraintViolation(ConstraintViolationException ex){
        String message = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath()+ " "+constraintViolation.getMessage())
                .collect(Collectors.toList()).toString();

        return new TodoListApiException(message,HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    TodoListApiException handleTaskNotFoundException(TaskNotFoundException exception) {
        String message = exception.getMessage();

        return new TodoListApiException(message,HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    @ExceptionHandler(TaskPatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    TodoListApiException handleTaskPatchException(TaskPatchException exception) {
        String message = exception.getMessage();

        return new TodoListApiException(message,HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }


}
