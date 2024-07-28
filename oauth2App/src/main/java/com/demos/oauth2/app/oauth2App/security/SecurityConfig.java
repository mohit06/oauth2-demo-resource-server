package com.demos.oauth2.app.oauth2App.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
//    private String introspectionUri;
//
//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
//    private String clientSecret;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConvertor = new JwtAuthenticationConverter();
        jwtAuthConvertor.setJwtGrantedAuthoritiesConverter(new KeycloalRoleConvertor());

        http.authorizeHttpRequests((req)-> req.requestMatchers("/index").hasRole("USER").anyRequest().permitAll())
                .oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConvertor)));
//                .oauth2ResourceServer(rsc -> rsc.opaqueToken(opaqueConfigurer -> opaqueConfigurer
//                        .introspectionUri(introspectionUri)
//                        .introspectionClientCredentials(clientId, clientSecret)
//                        .authenticationConverter(new KeycloakOpaqueRoleConvertor())));
        return http.build();
    }
}

