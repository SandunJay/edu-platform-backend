package com.userproject.demo.service;


import com.userproject.demo.auth.RegisterRequest;
import com.userproject.demo.dto.UserRequest;
import com.userproject.demo.dto.UserResponse;
import com.userproject.demo.token.TokenRepository;
import com.userproject.demo.user.User;
import com.userproject.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;


    public void adminCreateUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
        log.info("User {} created by admin " , user.getId());
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }
    private UserResponse mapToUserResponse(User user){
        return new UserResponse(user.getId(), user.getFirstname(), user.getLastname(),user.getEmail(), user.getRole());
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return mapToUserResponse(user);
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserResponse(user);
    }





    public void updateUser(Integer id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Update only the fields that are provided in the userRequest
        if (userRequest.getFirstname() != null) {
            user.setFirstname(userRequest.getFirstname());
        }
        if (userRequest.getLastname() != null) {
            user.setLastname(userRequest.getLastname());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null) {
            // Encrypt the password before saving
            String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());
            user.setPassword(encryptedPassword);
        }
        if (userRequest.getRole() != null) {
            user.setRole(userRequest.getRole());
        }

        userRepository.save(user);
        log.info("User {} updated", id);
        //return ResponseEntity.ok(service.register(user));
    }


    public void deleteUser(Integer id) {
        tokenRepository.deleteByUserId(id);
        userRepository.deleteById(id);
        log.info("User {} deleted", id);
    }


}
