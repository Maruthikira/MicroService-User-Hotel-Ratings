package com.gateway.ApiGateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresAt;
    private Collection<String> authorities;





}
