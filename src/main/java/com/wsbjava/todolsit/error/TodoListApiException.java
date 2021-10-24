package com.wsbjava.todolsit.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TodoListApiException {
    String massage;
    HttpStatus status;
    LocalDateTime timestamp;


}
