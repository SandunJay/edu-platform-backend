package com.userproject.demo.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.userproject.demo.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private Role role;



    public String getUserDetails(org.apache.catalina.User user) {
        if (user != null) {

            return user.getUsername();
        } else {

            return "User is not authenticated";
        }
    }



}
