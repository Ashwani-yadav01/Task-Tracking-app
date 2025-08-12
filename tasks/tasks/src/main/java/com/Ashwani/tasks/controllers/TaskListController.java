package com.Ashwani.tasks.controllers;

import com.Ashwani.tasks.domain.dtos.TaskListDto;
import com.Ashwani.tasks.domain.entities.TaskList;
import com.Ashwani.tasks.mappers.TaskListMapper;
import com.Ashwani.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
@RequiredArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }
}
