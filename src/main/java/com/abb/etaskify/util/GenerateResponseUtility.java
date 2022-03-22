package com.abb.etaskify.util;

import com.abb.etaskify.db.entity.Task;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.response.OrganizationResponse;
import com.abb.etaskify.model.response.TaskResponse;
import com.abb.etaskify.model.response.UserResponse;

import java.util.List;

public class GenerateResponseUtility {
    public static GenerateResponse<Integer, String, OrganizationResponse, ResponseData<OrganizationResponse>> orgFunc = (code, message, data) ->
            ResponseData.<OrganizationResponse>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();


    public static GenerateResponse<Integer, String, UserResponse, ResponseData<UserResponse>> userFunc = (code, message, data) ->
            ResponseData.<UserResponse>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();

    public static GenerateResponse<Integer, String, TaskResponse, ResponseData<TaskResponse>> taskFunc = (code, message, data) ->
            ResponseData.<TaskResponse>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();

    public static GenerateResponse<Integer, String, List<Task>, ResponseData<List<Task>>> listTaskFunc = (code, message, data) ->
            ResponseData.<List<Task>>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();
}
