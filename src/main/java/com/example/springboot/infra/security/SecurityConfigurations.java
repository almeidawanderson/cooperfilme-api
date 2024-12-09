package com.example.springboot.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/scripts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/scripts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/scripts/{id}").permitAll()
                        // endpoints protegidos do usuário comum, ainda não implementados.
                        .requestMatchers(HttpMethod.GET, "/scripts/status").permitAll()
                        .requestMatchers(HttpMethod.GET, "/scripts").hasAnyRole("ANALISTA", "REVISOR", "APROVADOR")
                        .requestMatchers(HttpMethod.PUT, "/scripts/status").hasAnyRole("ANALISTA", "REVISOR", "APROVADOR")
                        .requestMatchers(HttpMethod.PUT, "/scripts/vote").hasRole("APROVADOR")
                        .anyRequest().authenticated()
                )
                .build();
    }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
   }

}
