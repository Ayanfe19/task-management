package dev.ayanfe.taskmanagement.service;

import dev.ayanfe.taskmanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
