package com.userservice.userservice.service;

import com.userservice.userservice.dto.UserDto;
import com.userservice.userservice.model.User;

public interface UserService {

    User save (UserDto userDto);


}