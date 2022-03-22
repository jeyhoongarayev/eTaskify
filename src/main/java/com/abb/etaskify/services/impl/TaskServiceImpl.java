package com.abb.etaskify.services.impl;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.db.repository.TaskRepository;
import com.abb.etaskify.mapper.TaskMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.enums.TaskStatus;
import com.abb.etaskify.model.request.TaskRequest;
import com.abb.etaskify.model.response.TaskResponse;
import com.abb.etaskify.services.TaskService;
import com.abb.etaskify.util.GenerateResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public ResponseData<List<Task>> getAllTasksByOrganization(String idOrganization) {
        List<Task> list = taskRepository.findByIdOrganizationAndTaskStatusNot(idOrganization, TaskStatus.DELETED);
        if (list.isEmpty()){
            return GenerateResponseUtility.listTaskFunc.generate(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return GenerateResponseUtility.listTaskFunc.generate(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
    }

    @Override
    public ResponseData<List<Task>> getAllTasksByUser(String idUser) {
        List<Task> list = taskRepository.findByIduserAndTaskStatusNot(idUser, TaskStatus.DELETED);
        if (list.isEmpty()){
            return GenerateResponseUtility.listTaskFunc.generate(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
        return GenerateResponseUtility.listTaskFunc.generate(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
    }


    @Override
    public ResponseData<TaskResponse> create(TaskRequest taskRequest) {
        Task savedTask = taskRepository.save(taskMapper.toTask(taskRequest));
        return GenerateResponseUtility.taskFunc.generate(200,"SUCCESS", taskMapper.toTaskResponse(savedTask));
    }

    @Override
    public ResponseData<TaskResponse> update(TaskRequest taskRequest, String taskId) {
        Optional<Task> optionalUser = taskRepository.findByIdAndTaskStatusNot(taskId, TaskStatus.DELETED);
        if (optionalUser.isPresent()){
            Task task = taskMapper.toTask(taskRequest);
            task.setId(taskId);
            Task savedTask = taskRepository.save(task);
            return GenerateResponseUtility.taskFunc.generate(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), taskMapper.toTaskResponse(savedTask));
        }else{
            return GenerateResponseUtility.taskFunc.generate(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
    }




}
