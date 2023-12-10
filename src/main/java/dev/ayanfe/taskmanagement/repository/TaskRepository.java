package dev.ayanfe.taskmanagement.repository;

import dev.ayanfe.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
