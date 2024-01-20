package dev.ayanfe.taskmanagement.service.impl;

import dev.ayanfe.taskmanagement.dto.LoginDto;
import dev.ayanfe.taskmanagement.dto.RegisterDto;
import dev.ayanfe.taskmanagement.entity.Role;
import dev.ayanfe.taskmanagement.entity.User;
import dev.ayanfe.taskmanagement.exception.TaskAPIException;
import dev.ayanfe.taskmanagement.repository.RoleRepository;
import dev.ayanfe.taskmanagement.repository.UserRepository;
import dev.ayanfe.taskmanagement.security.JwtTokenProvider;
import dev.ayanfe.taskmanagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // Check if Username exists in db
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new TaskAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new TaskAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
