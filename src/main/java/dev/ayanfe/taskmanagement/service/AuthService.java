package dev.ayanfe.taskmanagement.service;

import dev.ayanfe.taskmanagement.dto.LoginDto;
import dev.ayanfe.taskmanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
