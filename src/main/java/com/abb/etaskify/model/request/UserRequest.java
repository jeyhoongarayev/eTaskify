package com.abb.etaskify.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String username;
    String email;

    @Size(min = 6, max = 20)
    String password;

    String role;
}
