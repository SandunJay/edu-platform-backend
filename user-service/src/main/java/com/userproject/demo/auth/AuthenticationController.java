package com.userproject.demo.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
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
            @RequestBody UserDTO userDTO
    ) {
        RegisterRequest request = new RegisterRequest();
        request.setEmail(userDTO.getEmail());
        request.setPassword(userDTO.getPassword());


        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        String cookieValue = request.getEmail() + "|" + authenticationResponse.getAccessToken();

        Cookie userCookie = new Cookie("userCookie", cookieValue);
        userCookie.setMaxAge(3600);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/access-user-cookie")
    public ResponseEntity<String> accessUserCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    String cookieValue = cookie.getValue();
                    String[] parts = cookieValue.split("\\|");
                    if (parts.length == 2) {
                        String email = parts[0];
                        String accessToken = parts[1];
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
