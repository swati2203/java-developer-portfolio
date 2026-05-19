package com.rahul.authservice.controller;

import com.rahul.authservice.dto.*;
import com.rahul.authservice.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = tokenProvider.generateToken(auth);
        return ResponseEntity.ok(new TokenResponse(token, "Bearer", 86400000L));
    }
}
