package com.wsbjava.todolsit.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.wsbjava.todolsit.model.Task;
import com.wsbjava.todolsit.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/tasks")
public class TodoListController {

    private final TaskService taskService;

    public TodoListController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Iterable<Task> getListOfTasks(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadlineAfter) {

        return taskService.getTask(deadlineAfter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){

        return taskService.createTask(task);
    }

    @GetMapping(path = "/{id}")
    public Task getTaskById(@PathVariable Integer id){

        return taskService.getTaskById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateTask(@PathVariable Integer id, @RequestBody Task task){
        taskService.updateTask(id, task);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void patchTask(@PathVariable Integer id, @RequestBody JsonPatch jsonPatch) throws JsonPatchException {
        taskService.patchTask(id,jsonPatch);
    }

    @DeleteMapping(path ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);

    }

}
