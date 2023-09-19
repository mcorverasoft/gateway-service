package com.mcorvera.mibanca.gatewayservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("**/actuator/**").permitAll()
                .antMatchers("**/test-prop/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();
        httpSecurity.oauth2ResourceServer()
                .jwt();
        return httpSecurity.build();
    }
}
