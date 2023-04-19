package com.example.Film_rating.config;

import com.example.Film_rating.controller.error.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeHttpRequests(
                    (requests) -> requests
                    .requestMatchers("/", "/films/**", "/registration").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/admin-page").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/admin-page").hasAuthority("ADMIN")
                    .anyRequest().authenticated())
            .formLogin((form) -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/films")
                    .permitAll())
            .logout((logout) -> logout.permitAll())
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }
}
