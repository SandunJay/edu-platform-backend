package com.userproject.demo.auth;

import com.userproject.demo.user.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;


}
