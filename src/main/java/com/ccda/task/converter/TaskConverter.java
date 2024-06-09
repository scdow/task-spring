package com.ccda.task.converter;

import com.ccda.task.dao.Task;
import com.ccda.task.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskConverter {
    public static TaskDTO convertTask (Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setCode(task.getCode());
        taskDTO.setCreate_time(task.getCreate_time());
        taskDTO.setUpdate_time(task.getUpdate_time());
        return taskDTO;
    }

    public static List<TaskDTO> convertTask (List<Task> taskList){
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task : taskList){
            taskDTOList.add(convertTask(task));
        }
        return taskDTOList;
    }

    public static Task convertTask (TaskDTO taskDTO){
        Task task = new Task();
        task.setName(taskDTO.getName());
//        task.setCode(taskDTO.getCode());
//        task.setCreate_time(taskDTO.getCreate_time());
//        task.setUpdate_time(taskDTO.getUpdate_time());
        return task;
    }
}
