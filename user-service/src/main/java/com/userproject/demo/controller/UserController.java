package com.userproject.demo.controller;

import com.userproject.demo.dto.UserRequest;
import com.userproject.demo.dto.UserResponse;
import com.userproject.demo.service.UserService;
import com.userproject.demo.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenRepository tokenRepository;

    @GetMapping
    @ResponseStatus
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{email}")
    public UserResponse getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }


    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }



    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        tokenRepository.deleteByUserId(id);
        userService.deleteUser(id);
    }



}
