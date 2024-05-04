package com.techie.adminTaskService.client;

import com.techie.adminTaskService.dto.UserRequest;
import com.techie.adminTaskService.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service" , url = "http://localhost:8092")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users")
    List<UserResponse> getAllUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/{id}")
    UserResponse getUserById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/user/{id}")
    UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/user/{id}")
    void deleteUser(@PathVariable Long id);
}
