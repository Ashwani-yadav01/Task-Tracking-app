package com.Ashwani.tasks.mappers.impl;

import com.Ashwani.tasks.domain.dtos.TaskListDto;
import com.Ashwani.tasks.domain.entities.Task;
import com.Ashwani.tasks.domain.entities.TaskList;
import com.Ashwani.tasks.domain.entities.TaskStatus;
import com.Ashwani.tasks.mappers.TaskListMapper;
import com.Ashwani.tasks.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapper taskMapper;

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(taskDtos -> taskDtos.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(null),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks) return null;
        long closedTaskCount = tasks.stream().
                filter(task -> TaskStatus.CLOSED == task.getStatus()).count();

        return (double) (closedTaskCount / tasks.size());

    }
}
