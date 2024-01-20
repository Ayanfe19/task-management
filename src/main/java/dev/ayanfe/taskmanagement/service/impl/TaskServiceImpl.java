package dev.ayanfe.taskmanagement.service.impl;

import dev.ayanfe.taskmanagement.dto.TaskDto;
import dev.ayanfe.taskmanagement.entity.Task;
import dev.ayanfe.taskmanagement.exception.ResourceNotFoundException;
import dev.ayanfe.taskmanagement.repository.TaskRepository;
import dev.ayanfe.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    private ModelMapper modelMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        // Convert TaskDto into Task Jpa Entity
        Task task = modelMapper.map(taskDto, Task.class);

        // Task Jpa entity
        Task savedTask = taskRepository.save(task);

        // Convert savedTask Jpa entity object into TaskDto object
        TaskDto savedTaskDto = modelMapper.map(savedTask, TaskDto.class);
        return savedTaskDto;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task with given id: " + taskId + " does not exist"));

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with given id: " + taskId + " does not exist")
        );

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        Task updatedTaskObj = taskRepository.save(task);
        return modelMapper.map(updatedTaskObj, TaskDto.class);
    }

    @Override
    public void deleteTask(Long taskId) {

    }

    @Override
    public void deleteTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with given id: " + taskId + " does not exist")
        );

        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto markAsComplete(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with given id: " + taskId + " does not exist")
        );

        task.setCompleted(Boolean.TRUE);

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public TaskDto markIncomplete(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with given id: " + taskId + " does not exist")
        );

        task.setCompleted(Boolean.FALSE);

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDto.class);
    }
}
