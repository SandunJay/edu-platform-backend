package com.userproject.demo.dto;

import com.userproject.demo.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//    public record UserResponse(Integer id, String firstname, String lastname, String email, Role role) {
//
//    }

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}

