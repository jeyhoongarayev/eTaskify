package com.abb.etaskify.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.db.repository.UserRepository;
import com.abb.etaskify.mapper.UserMapper;
import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.UserRequest;
import com.abb.etaskify.model.response.UserResponse;
import com.abb.etaskify.security.PasswordConfig;

import java.net.http.HttpClient;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class, PasswordConfig.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    private static final String EMAIL = "random@gmail.com";
    private static final String ID = "1";
    private static final String ID_ORGANIZATION = "organization";
    private static final String PASSWORD = "123456";
    private static final String ROLE = "ADMIN";
    private static final String USERNAME = "random";
    private static final String ID_USER = "2";

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testCreate() {
        User user = new User();
        User user1 = new User();
        Optional<User> ofResult = Optional.of(user1);
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        UserRequest userRequest = new UserRequest(USERNAME, EMAIL, PASSWORD, ROLE);

        ResponseData<UserResponse> actualCreateResult = this.userServiceImpl.create(userRequest);
        assertEquals(409, actualCreateResult.getCode());
        assertEquals("ALREADY_EXIST", actualCreateResult.getMessage());
        assertNull(actualCreateResult.getData());
        verify(this.userRepository).findByUsername((String) any());
        assertEquals("USER", userRequest.getRole());
    }

    @Test
    void testCreate2() {
        User user = new User();
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findByUsername((String) any())).thenReturn(Optional.empty());

        User user1 = new User();
        when(this.userMapper.toUser((UserRequest) any())).thenReturn(user1);
        UserResponse userResponse = new UserResponse(USERNAME, EMAIL, PASSWORD, ROLE);

        when(this.userMapper.toUserResponse((User) any())).thenReturn(userResponse);
        UserRequest userRequest = new UserRequest(USERNAME, EMAIL, PASSWORD, ROLE);

        ResponseData<UserResponse> actualCreateResult = this.userServiceImpl.create(userRequest);
        assertEquals(200, actualCreateResult.getCode());
        assertEquals("SUCCESS", actualCreateResult.getMessage());
        assertSame(userResponse, actualCreateResult.getData());
        verify(this.userRepository).save((User) any());
        verify(this.userRepository).findByUsername((String) any());
        verify(this.userMapper).toUser((UserRequest) any());
        verify(this.userMapper).toUserResponse((User) any());
        assertEquals("USER", userRequest.getRole());
    }

    @Test
    void testUpdate() {
        User user = new User();
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findByIdAndIsActive((String) any(), (Boolean) any())).thenReturn(ofResult);

        User user2 = new User();
        UserResponse userResponse = new UserResponse(USERNAME, EMAIL, PASSWORD, ROLE);

        when(this.userMapper.toUserResponse((User) any())).thenReturn(userResponse);
        when(this.userMapper.toUser((UserRequest) any())).thenReturn(user2);
        ResponseData<UserResponse> actualUpdateResult = this.userServiceImpl
                .update(new UserRequest(USERNAME, EMAIL, PASSWORD, ROLE), ID_USER);
        assertEquals(HttpStatus.CREATED.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.CREATED.name(), actualUpdateResult.getMessage());
        assertSame(userResponse, actualUpdateResult.getData());
        verify(this.userRepository).save((User) any());
        verify(this.userRepository).findByIdAndIsActive((String) any(), (Boolean) any());
        verify(this.userMapper).toUser((UserRequest) any());
        verify(this.userMapper).toUserResponse((User) any());
    }

    @Test
    void testUpdate2() {
        User user = new User();
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findByIdAndIsActive((String) any(), (Boolean) any())).thenReturn(Optional.empty());

        User user1 = new User();
        when(this.userMapper.toUserResponse((User) any()))
                .thenReturn(new UserResponse(USERNAME, EMAIL, PASSWORD, ROLE));
        when(this.userMapper.toUser((UserRequest) any())).thenReturn(user1);
        ResponseData<UserResponse> actualUpdateResult = this.userServiceImpl
                .update(new UserRequest(USERNAME, EMAIL, PASSWORD, ROLE), ID_USER);
        assertEquals(HttpStatus.NOT_FOUND.value(), actualUpdateResult.getCode());
        assertEquals(HttpStatus.NOT_FOUND.name(), actualUpdateResult.getMessage());
        assertNull(actualUpdateResult.getData());
        verify(this.userRepository).findByIdAndIsActive((String) any(), (Boolean) any());
    }
}

