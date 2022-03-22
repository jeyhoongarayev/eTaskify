package com.abb.etaskify.services.impl;

import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.db.repository.UserRepository;
import com.abb.etaskify.mapper.UserMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.UserRequest;
import com.abb.etaskify.model.response.UserResponse;
import com.abb.etaskify.security.PasswordConfig;
import com.abb.etaskify.services.UserService;
import com.abb.etaskify.util.Constants;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordConfig passwordConfig;

    @Override
    public ResponseData<UserResponse> create(UserRequest userRequest) {
        userRequest.setRole(Constants.DEFAULT_ROLE);
        userRequest.setPassword(passwordConfig.passwordEncoder().encode(Constants.DEFAULT_PASSWORD));
        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());
        if (optionalUser.isEmpty()) {
            User savedUser = userRepository.save(userMapper.toUser(userRequest));
            return GenerateResponseUtility.userFunc.generate(200, "SUCCESS", userMapper.toUserResponse(savedUser));
        }
        return GenerateResponseUtility.userFunc.generate(409, "ALREADY_EXIST", null);
    }

    @Override
    public ResponseData<UserResponse> update(UserRequest userRequest, String userId) {
        Optional<User> optionalUser = userRepository.findByIdAndIsActive(userId, true);
        if (optionalUser.isPresent()){
            User user = userMapper.toUser(userRequest);
            user.setId(userId);
            User savedUser = userRepository.save(user);
            return GenerateResponseUtility.userFunc.generate(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), userMapper.toUserResponse(savedUser));
        }else{
            return GenerateResponseUtility.userFunc.generate(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        }
    }


}
