package com.abb.etaskify.services;

import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.UserRequest;
import com.abb.etaskify.model.response.UserResponse;

import java.util.List;

public interface UserService {


    ResponseData<UserResponse> create(UserRequest userRequest);

    ResponseData<UserResponse> update(UserRequest userRequest, String userId);

}
