package com.userproject.demo.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//@PostMapping("/authenticate")
//public ResponseEntity<AuthenticationResponse> authenticate(
//        @RequestBody AuthenticationRequest request,
//        HttpServletResponse response // Inject HttpServletResponse to modify response
//) {
//    // Authenticate the user and obtain the authentication response
//    AuthenticationResponse authenticationResponse = service.authenticate(request);
//
//    // Create a cookie with the user details
//    Cookie userCookie = new Cookie("userCookie", authenticationResponse.getUserDetails());
//
//    // Set additional properties for the cookie
//    userCookie.setMaxAge(3600); // Set cookie expiration time (in seconds), e.g., 1 hour
//    userCookie.setPath("/"); // Set cookie path to root path
//
//    // Add the cookie to the response
//    response.addCookie(userCookie);
//
//    // Return the authentication response
//    return ResponseEntity.ok(authenticationResponse);
//}

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request,
//            HttpServletResponse response // Inject HttpServletResponse to modify response
//    ) {
//        // Authenticate the user and obtain the authentication response
//        AuthenticationResponse authenticationResponse = service.authenticate(request);
//
//        System.out.println(authenticationResponse);
//        // Create a cookie with the access token
//        Cookie accessTokenCookie = new Cookie("accessToken", authenticationResponse.getAccessToken());
//
//        // Set additional properties for the cookie
//        accessTokenCookie.setMaxAge(3600); // Set cookie expiration time (in seconds), e.g., 1 hour
//        accessTokenCookie.setPath("/"); // Set cookie path to root path
//
//        // Add the cookie to the response
//        response.addCookie(accessTokenCookie);
//
//        // Return the authentication response
//        return ResponseEntity.ok(authenticationResponse);
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response // Inject HttpServletResponse to modify response
    ) {
        // Authenticate the user and obtain the authentication response
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        System.out.println(authenticationResponse);

        // Serialize email and access token into a single string
        String cookieValue = request.getEmail() + "|" + authenticationResponse.getAccessToken();

        // Create a cookie with the combined value
        Cookie userCookie = new Cookie("userCookie", cookieValue);
        // Set additional properties for the user cookie
        userCookie.setMaxAge(3600); // Set cookie expiration time (in seconds), e.g., 1 hour
        userCookie.setPath("/"); // Set cookie path to root path
        // Add the user cookie to the response
        response.addCookie(userCookie);

        // Return the authentication response
        return ResponseEntity.ok(authenticationResponse);
    }


    @GetMapping("/access-user-cookie")
    public ResponseEntity<String> accessUserCookie(HttpServletRequest request) {
        // Retrieve cookies from the request
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    // Get the value of the userCookie
                    String cookieValue = cookie.getValue();
                    // Split the value to extract email and access token
                    String[] parts = cookieValue.split("\\|");
                    if (parts.length == 2) {
                        String email = parts[0];
                        String accessToken = parts[1];
                        // Do something with email and accessToken
                        return ResponseEntity.ok("Email: " + email + ", Access Token: " + accessToken);
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


    @GetMapping("/check-user-validity")
    public ResponseEntity<Boolean> checkUserValidity(@RequestBody String jwtToken) {
        System.out.println(jwtToken);
        boolean isValidUser = service.isValidUser(jwtToken);
        System.out.println(isValidUser);

        return ResponseEntity.ok(isValidUser);
    }

}
