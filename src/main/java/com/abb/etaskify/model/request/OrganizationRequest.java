package com.abb.etaskify.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationRequest {

    String username;
    String organizationName;
    String phoneNumber;
    String address;
    String email;
    String password;
    String role;

}
