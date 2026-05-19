package com.rahul.authservice.dto;
import lombok.*;
@Data @AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}
