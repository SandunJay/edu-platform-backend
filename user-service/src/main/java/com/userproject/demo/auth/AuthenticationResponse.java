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
    //private Role role;

//    public String getUserDetails() {
//        // Assuming you have a User object representing the authenticated user
//        // You would return some information about the user as a string
//        // For example, you might return the username
//        User user = null;
//        return user.getUsername();
//    }

    public String getUserDetails(org.apache.catalina.User user) {
        if (user != null) {
            // Assuming you have a User object representing the authenticated user
            // You would return some information about the user as a string
            // For example, you might return the username
            return user.getUsername();
        } else {
            // Handle the case when user is null
            return "User is not authenticated";
        }
    }


//
//    public String getRole() {
//        return role;
//    }



    //private String token;
}
