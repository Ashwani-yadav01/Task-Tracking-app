package com.Ashwani.tasks.services.Impl;

import com.Ashwani.tasks.domain.entities.TaskList;
import com.Ashwani.tasks.repositories.TaskListRepository;
import com.Ashwani.tasks.repositories.TaskRepository;
import com.Ashwani.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null !=taskList.getId()){
            throw new IllegalArgumentException("Task List already has an ID!");
        }
        if(null==taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task List title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
