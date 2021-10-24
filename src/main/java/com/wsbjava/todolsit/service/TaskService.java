package com.wsbjava.todolsit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.wsbjava.todolsit.error.TaskNotFoundException;
import com.wsbjava.todolsit.error.TaskPatchException;
import com.wsbjava.todolsit.model.Task;
import com.wsbjava.todolsit.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    public TaskService(TaskRepository taskRepository, ObjectMapper objectMapper) {
        this.taskRepository = taskRepository;
        this.objectMapper = objectMapper;
    }

    public Iterable<Task> getTask(LocalDate deadlineAfter){
        System.out.println(deadlineAfter);
        if(deadlineAfter!=null){
            return taskRepository.findAllByDeadlineAfter(deadlineAfter,
                    Sort.by(Sort.Direction.DESC, "deadline"));
        }
        return taskRepository.findAllByIsVisibleIsTrue();
    }

    public Task createTask(@Valid Task task){

        return taskRepository.save(task);
    }

    public Task getTaskById(Integer id){

        return taskRepository.findTaskByIdEqualsAndIsVisibleIsTrue(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Transactional
    public void updateTask(Integer id, @Valid Task task){
        Task taskToUpdate = taskRepository.findTaskByIdEqualsAndIsVisibleIsTrue(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setId(taskToUpdate.getId());
        taskRepository.save(task);
    }

    @Transactional
    public void patchTask(Integer id, JsonPatch jsonPatch) throws JsonPatchException {
       Task taskToPatch = taskRepository.findTaskByIdEqualsAndIsVisibleIsTrue(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        JsonNode node = objectMapper.valueToTree(taskToPatch);

        JsonNode updatedJson;
        try{
            updatedJson = jsonPatch.apply(node);
        }
        catch (Exception e){
            throw new TaskPatchException(e.getMessage());
        }
        Task updatedTask = objectMapper.convertValue(updatedJson, Task.class);
        updatedTask.setId(taskToPatch.getId());
        taskRepository.save(updatedTask);

    }

    @Transactional
    public void deleteTask(Integer id){
        Task taskToDelete =taskRepository.findTaskByIdEqualsAndIsVisibleIsTrue(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskToDelete.setIsVisible(false);
        taskRepository.save(taskToDelete);
    }
}
