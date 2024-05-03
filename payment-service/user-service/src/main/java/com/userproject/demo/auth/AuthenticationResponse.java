package com.userproject.demo.auth;

import ch.qos.logback.core.net.SMTPAppenderBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

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


    //private String token;
}
