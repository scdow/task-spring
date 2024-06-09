package com.ccda.task.service;

import com.ccda.task.dao.Task;
import com.ccda.task.dto.TaskDTO;

import java.util.Date;
import java.util.List;

public interface TaskService {
    Task getTaskById(long id);

    TaskDTO getTaskDTOById(long id);

    List<TaskDTO> getAllTaskDTO();

    List<TaskDTO> getCurrentTaskDTO();

    List<TaskDTO> queryTaskDTO(String name, String code, Date startDate, Date endDate);

    Long addNewTask(TaskDTO taskDTO);

    void deleteTaskById(long id);

    TaskDTO updateTaskById(long id, String name);

    TaskDTO hideTaskById(long id);

}
