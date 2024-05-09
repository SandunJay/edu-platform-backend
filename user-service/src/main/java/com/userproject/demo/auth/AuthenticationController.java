package com.userproject.demo.auth;

import com.userproject.demo.token.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDTO userDTO , HttpServletResponse response
    ) throws IOException {
        RegisterRequest request = new RegisterRequest();
        request.setFirstname(userDTO.getFirstname());
        request.setLastname(userDTO.getLastname());
        request.setEmail(userDTO.getEmail());
        request.setPassword(userDTO.getPassword());
        request.setRole(userDTO.getRole());



        // Redirect based on user's role
//        String redirectUrl = getRedirectUrl(String.valueOf(userDTO.getRole()));
//        response.sendRedirect(redirectUrl);


        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) throws IOException {
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        String cookieValue = request.getEmail()  + "|" + authenticationResponse.getAccessToken();

        Cookie userCookie = new Cookie("userCookie", cookieValue);
        userCookie.setMaxAge(3600);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        // Extract user role from access token
        //String userRole = jwtTokenUtil.extractUserRoleFromToken(authenticationResponse.getAccessToken());

        /// Redirect based on user's role
//        String redirectUrl = getRedirectUrl(userRole);
//        response.sendRedirect(redirectUrl);

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


    private String getRedirectUrl(String role) {
        switch (role) {
            case "ADMIN":
                return "/admin/dashboard";
            case "USER":
                return "/user/dashboard";
            case "INSTRUCTOR":
                return "/instructor/dashboard";
            default:
                return "/dashboard";
        }
    }

}
