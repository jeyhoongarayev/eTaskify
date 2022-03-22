package com.abb.etaskify.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationResponse {

    String username;
    String organizationName;
    String phoneNumber;
    String address;
    String email;
    String password;
    String role;

}
