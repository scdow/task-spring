package com.ccda.task.service;

import com.ccda.task.dao.Task;
import com.ccda.task.dto.TaskDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface TaskService {
    Task getTaskById(long id);

    TaskDTO getTaskDTOById(long id);

    List<TaskDTO> getAllTaskDTO();

    List<TaskDTO> getCurrentTaskDTO();

    Page<TaskDTO> getCurrentPagedTaskDTO(int page, int size);

    List<TaskDTO> queryTaskDTO(String name, String code, Date startDate, Date endDate);

    Page<TaskDTO> queryPagedTaskDTO(int page, int size, String name, String code, Date startDate, Date endDate);

    Long addNewTask(TaskDTO taskDTO);

    void deleteTaskById(long id);

    TaskDTO updateTaskById(long id, String name);

    TaskDTO hideTaskById(long id);



}
