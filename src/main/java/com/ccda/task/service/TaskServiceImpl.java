package com.ccda.task.service;

import com.ccda.task.converter.TaskConverter;
import com.ccda.task.dao.Task;
import com.ccda.task.dao.TaskRepository;
import com.ccda.task.dto.TaskDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ccda.task.converter.TaskConverter.convertTask;

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
        return convertTask(task);
    }

    @Override
    public List<TaskDTO> getAllTaskDTO(){
        List<Task> taskList = taskRepository.findAll();
        return convertTask(taskList);
    }

    @Override
    public List<TaskDTO> getCurrentTaskDTO(){
        List<Task> taskList = taskRepository.findByDeleted(false);
        return convertTask(taskList);
    }

    @Override
//    分页
    public Page<TaskDTO> getCurrentPagedTaskDTO(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
//        Page<Task> taskPage = taskRepository.findPageByDeletedFalse(pageable);
        Page<Task> taskPage = taskRepository.findPageByDeleted(pageable, false);
        List<TaskDTO> taskDTOs = taskPage.getContent().stream()
                .map(task -> convertTask(task))  // convert each Task to TaskDTO
                .collect(Collectors.toList());
        return new PageImpl<>(taskDTOs, pageable, taskPage.getTotalElements());
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
        return convertTask(taskList);
    }


    @Override
    public Page<TaskDTO> queryPagedTaskDTO(int page, int size, String name, String code, Date startDate, Date endDate){
        // 创建一个默认的Pageable对象
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage = null;

        if (startDate != null && endDate != null){
            taskPage = taskRepository.findByPagedQuery(name, code, startDate, endDate, pageable);
            System.out.println(startDate);
            System.out.println(endDate);
        }
        else{
            taskPage = taskRepository.findByPagedQueryWithoutDate(name, code, pageable);
            System.out.println("without date");
        }

        System.out.println(taskPage);
        List<TaskDTO> taskDTOs = taskPage.getContent().stream()
                .map(task -> convertTask(task))  // convert each Task to TaskDTO
                .collect(Collectors.toList());
        System.out.println(taskDTOs);
        return new PageImpl<>(taskDTOs, pageable, taskPage.getTotalElements());
    }


    @Override
    public Long addNewTask(TaskDTO taskDTO){
        List<Task> taskList = taskRepository.findByName(taskDTO.getName());
//        允许name重复，因为code不同
//        if (!CollectionUtils.isEmpty(taskList)){
//            throw new IllegalStateException("name: " + taskDTO.getName() + " has been taken");
//        }
        Task task = taskRepository.save(convertTask(taskDTO));
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
        return convertTask(task);
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
        return convertTask(task);
    }



}
