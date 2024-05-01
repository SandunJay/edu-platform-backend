package com.techie.adminTaskService.dto;

public record UserRequest(String email, String password, String role, String fullname) {
}
