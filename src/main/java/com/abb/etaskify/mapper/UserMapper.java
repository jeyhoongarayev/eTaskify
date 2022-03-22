package com.abb.etaskify.mapper;

import com.abb.etaskify.db.entity.User;
import com.abb.etaskify.model.request.UserRequest;
import com.abb.etaskify.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

}
