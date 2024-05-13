package com.userproject.demo.auth;

import com.userproject.demo.token.JwtTokenUtil;
import com.userproject.demo.user.Role;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.*;

@CrossOrigin(origins = "http://localhost:3000")
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




        return ResponseEntity.ok(service.register(request));
    }





    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response

    ) throws IOException {
        // Authenticate the user and get the authentication response
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        String email = request.getEmail();

        // Fetch user role from the database based on email
        String role = fetchUserRoleByEmail(email);

        if (role == null) {
            // Handle case where user role is not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Set the user role in the authentication response
        authenticationResponse.setRole(Role.valueOf(role));



        // Return the authentication response with the updated role
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





    private String fetchUserRoleByEmail(String email) {
        String role = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwt_security", "root", "mH@cW98#$365mySQL");
             PreparedStatement statement = connection.prepareStatement("SELECT role FROM _user WHERE email = ?");
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    role = resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
        return role;
    }


}
