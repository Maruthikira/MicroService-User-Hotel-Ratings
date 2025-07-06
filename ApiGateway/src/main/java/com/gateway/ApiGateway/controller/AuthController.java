package com.gateway.ApiGateway.controller;


import com.gateway.ApiGateway.models.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = Logger.getLogger(AuthController.class.getName());
    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user) {

        logger.info("User email"+ user.getEmail());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getEmail());

        // Access token comes from client
        authResponse.setAccessToken(client.getAccessToken().getTokenValue());

        // Refresh token may be null (ensure it's not null in real use cases)
        if (client.getRefreshToken() != null) {
            authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        }

        // Token expiration
        if (client.getAccessToken().getExpiresAt() != null) {
            authResponse.setExpiresAt(
                    String.valueOf(client.getAccessToken().getExpiresAt().getEpochSecond())
            );
        }


        List<String> authorities = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList();
        authResponse.setAuthorities(authorities);
        return new ResponseEntity<>(authResponse, org.springframework.http.HttpStatus.OK);
    }

}
