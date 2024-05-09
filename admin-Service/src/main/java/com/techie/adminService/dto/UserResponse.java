package com.techie.adminService.dto;

public record UserResponse(String email, String password, String role, String fullname) {
}
