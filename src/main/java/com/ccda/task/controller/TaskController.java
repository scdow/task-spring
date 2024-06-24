package com.ccda.task.controller;

import com.ccda.task.Response;
import com.ccda.task.dao.Task;
import com.ccda.task.dto.TaskDTO;
import com.ccda.task.service.TaskService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:7070")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable long id){
        return taskService.getTaskById(id);
    }

//    查询接口
    @GetMapping("/taskdto/{id}")
    public Response<TaskDTO> getTaskDTOById(@PathVariable long id){
        return Response.newSuccess(taskService.getTaskDTOById(id));
    }

    //    GET所有任务
    @GetMapping("/taskdto/all")
    public Response<List<TaskDTO>> getAllTaskDTO(){
        return Response.newSuccess(taskService.getAllTaskDTO());
    }

    //    GET所有deleted==0的任务
    @GetMapping("/taskdto/current")
    public Response<List<TaskDTO>> getCurrentTaskDTO(){
        return Response.newSuccess(taskService.getCurrentTaskDTO());
    }

//    分页GET任务 并且deleted==0
    @GetMapping("/taskdto/current/paged")
    public Response<Page<TaskDTO>> getPagedTasks(@RequestParam int page, @RequestParam int size){
        return Response.newSuccess(taskService.getCurrentPagedTaskDTO(page, size));
    }



    //    模糊查询，GET被query的任务，指定name,code,create_date范围
    @GetMapping("/taskdto/query")
    public Response<List<TaskDTO>> queryTaskDTO(  @RequestParam(required = false)  String name, @RequestParam(required = false)  String code,
                                                   @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                   @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate){
        return Response.newSuccess(taskService.queryTaskDTO(name, code, startDate, endDate));
    }
//    @JsonFormat
//    @DateTimeFormat
//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//@DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")

    //    分页 + 模糊查询，GET被query的任务，指定name,code,create_date范围
    @GetMapping("/taskdto/query/paged")
    public Response<Page<TaskDTO>> queryPagedTaskDTO(@RequestParam int page, @RequestParam int size,
                                                     @RequestParam(required = false)  String name, @RequestParam(required = false)  String code,
                                                @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate){
        return Response.newSuccess(taskService.queryPagedTaskDTO(page, size, name, code, startDate, endDate));
    }


//    新增接口，新增任务
    @PostMapping("/task")
//    接受前端json
    public Response<Long> addNewTask(@RequestBody TaskDTO taskDTO){
        return Response.newSuccess(taskService.addNewTask(taskDTO));
    }

//    删除接口
    @DeleteMapping("/task/{id}")
    public void deleteTaskById(@PathVariable long id){
        taskService.deleteTaskById(id);
    }

//    更新接口，编辑name
    @PutMapping("task/{id}")
    public Response<TaskDTO> updateTaskById(@PathVariable long id, @RequestBody(required = false) String name){
        return Response.newSuccess(taskService.updateTaskById(id, name));
    }

    //    更新deleted，由0到1；更新delete_time，由null到date
    @PutMapping("task/hide/{id}")
    public Response<TaskDTO> hideTaskById(@PathVariable long id){
        return Response.newSuccess(taskService.hideTaskById(id));
    }
}

