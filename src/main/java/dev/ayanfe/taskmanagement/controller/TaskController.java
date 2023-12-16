package dev.ayanfe.taskmanagement.controller;

import dev.ayanfe.taskmanagement.dto.TaskDto;
import dev.ayanfe.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    // Add Task REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto savedTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    //Get Task by Id REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public  ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    // Get ALl Tasks REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Update Task REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId,
                                              @RequestBody TaskDto updatedTask){
        TaskDto taskDto = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(taskDto);
    }

    // Delete Task REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task Deleted Successfully!");
    }

    // Mark Task As Complete REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TaskDto> markAsComplete(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.markAsComplete(taskId);
        return ResponseEntity.ok(updatedTask);
    }


    // Mark Task As Incomplete REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TaskDto> markIncomplete(@PathVariable("id") Long taskId){
        TaskDto updatedTask = taskService.markIncomplete(taskId);
        return ResponseEntity.ok(updatedTask);
    }

}
