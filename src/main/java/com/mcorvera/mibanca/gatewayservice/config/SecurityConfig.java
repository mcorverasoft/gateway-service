package com.mcorvera.mibanca.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers("**/actuator/**").permitAll()
                                .pathMatchers(HttpMethod.GET,"**/test-prop").permitAll()
                                .pathMatchers("**/swagger-ui.html", "**/swagger-ui/**").permitAll()
                                .anyExchange().authenticated()
                );
        httpSecurity.oauth2ResourceServer().jwt();
        httpSecurity.oauth2Login();
        return httpSecurity.build();
    }
}
