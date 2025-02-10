package com.example.blog.app.controller;

import com.example.blog.app.domain.dto.AuthResponse;
import com.example.blog.app.domain.dto.LoginRequest;
import com.example.blog.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(), loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400)
                .build();
        return ResponseEntity.ok(authResponse);
    }
}
