package com.techie.adminService.dto;

public record UserRequest(String email, String password, String role, String fullname) {
}
