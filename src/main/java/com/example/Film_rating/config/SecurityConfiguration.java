package com.example.Film_rating.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/films/**", "/registration", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/delete/**").hasAnyRole("ADMIN")
//                        .requestMatchers("/add/**").hasAnyRole("ADMIN")
//                        .requestMatchers("/edit/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/films")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");

        return http.build();
    }
}
