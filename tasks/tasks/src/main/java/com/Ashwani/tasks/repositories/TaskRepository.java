package com.Ashwani.tasks.repositories;

import com.Ashwani.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListID(UUID taskListId);

    Optional<Task> findByTaskListIdAndId(UUID taskListID, UUID id);
}
