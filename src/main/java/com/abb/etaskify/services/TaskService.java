package com.abb.etaskify.services;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.TaskRequest;
import com.abb.etaskify.model.response.TaskResponse;

import java.util.List;

public interface TaskService {


    ResponseData<List<Task>> getAllTasksByOrganization(String idOrganization);
    ResponseData<List<Task>> getAllTasksByUser(String idOrganization);

    ResponseData<TaskResponse> create(TaskRequest taskRequest);

    ResponseData<TaskResponse> update(TaskRequest taskRequest, String taskid);

}
