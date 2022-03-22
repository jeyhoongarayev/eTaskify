package com.abb.etaskify.api;

import com.abb.etaskify.model.ResponseData;
import com.abb.etaskify.model.request.UserRequest;
import com.abb.etaskify.model.response.UserResponse;
import com.abb.etaskify.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseData<UserResponse>> create(UserRequest userRequest){
        return ResponseEntity.ok(userService.create(userRequest));
    }

}
