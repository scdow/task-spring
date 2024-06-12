package com.ccda.task.service;

import com.ccda.task.converter.TaskConverter;
import com.ccda.task.dao.Task;
import com.ccda.task.dao.TaskRepository;
import com.ccda.task.dto.TaskDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task getTaskById(long id){
        return taskRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public TaskDTO getTaskDTOById(long id){
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        return TaskConverter.convertTask(task);
    }

    @Override
    public List<TaskDTO> getAllTaskDTO(){
        List<Task> taskList = taskRepository.findAll();
        return TaskConverter.convertTask(taskList);
    }

    @Override
    public List<TaskDTO> getCurrentTaskDTO(){
        List<Task> taskList = taskRepository.findByDeleted(false);
        return TaskConverter.convertTask(taskList);
    }

    @Override
    public List<TaskDTO> queryTaskDTO(String name, String code, Date startDate, Date endDate){
        List<Task> taskList = new ArrayList<>();
        if (startDate!=null && endDate!=null){
            taskList = taskRepository.findByQuery(name, code, startDate, endDate);
            System.out.println(startDate);
            System.out.println(endDate);
        }
        else{
            taskList = taskRepository.findByQueryWithoutDate(name, code);
            System.out.println("without date");
        }
        return TaskConverter.convertTask(taskList);
    }


    @Override
    public Long addNewTask(TaskDTO taskDTO){
        List<Task> taskList = taskRepository.findByName(taskDTO.getName());
        if (!CollectionUtils.isEmpty(taskList)){
            throw new IllegalStateException("name: " + taskDTO.getName() + " has been taken");
        }
        Task task = taskRepository.save(TaskConverter.convertTask(taskDTO));
        return task.getId();
    }

    @Override
    public void deleteTaskById(long id){
        taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id "+id+" does not exist."));
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskDTO updateTaskById(long id, String name){
        Task taskInDB = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id "+id+" does not exist."));
        if(StringUtils.hasLength(name) && !taskInDB.getName().equals(name)){
            taskInDB.setName(name);
            System.out.println(name);
        }
        else{
            if(!StringUtils.hasLength(name)){
                System.out.println("new name is empty");
            }
            if(taskInDB.getName().equals(name)){
                System.out.println("same name");
            }
        }
        Task task = taskRepository.save(taskInDB);
        return TaskConverter.convertTask(task);
    }

    @Override
    @Transactional
    public TaskDTO hideTaskById(long id){
        Task taskInDB = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id "+id+" does not exist."));
        Task task = taskRepository.save(taskInDB);
        if (task.isDeleted()){
            throw new IllegalStateException("task: " + task.getName() + " has been deleted");
        }
        task.setDeleted(true);
        task.setDelete_time(new Date());
        return TaskConverter.convertTask(task);
    }

}
