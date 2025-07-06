package com.user.service.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    // This class is currently empty, but you can add your security configuration here.
    // For example, you can configure authentication, authorization, and other security-related settings.



        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer(oauth2 ->
                            oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()))
                    );

            return http.build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
            // Replace with your actual issuer URI (from Okta or your auth server)
            String issuerUri ="https://dev-08129458.okta.com/oauth2/default";
            return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
        }


}
