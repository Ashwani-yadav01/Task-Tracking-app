package com.Ashwani.tasks.mappers;

import com.Ashwani.tasks.domain.dtos.TaskListDto;
import com.Ashwani.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
