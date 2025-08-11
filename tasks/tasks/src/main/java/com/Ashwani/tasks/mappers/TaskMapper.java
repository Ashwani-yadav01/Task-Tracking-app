package com.Ashwani.tasks.mappers;

import com.Ashwani.tasks.domain.dtos.TaskDto;
import com.Ashwani.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
