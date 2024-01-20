package dev.ayanfe.taskmanagement.service;

import dev.ayanfe.taskmanagement.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long taskId);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(Long taskId, TaskDto updatedTask);

    void deleteTask(Long taskId);

    void deleteTask(long taskId);

    abstract TaskDto markAsComplete(Long taskId);

    TaskDto markIncomplete(Long taskId);
}
