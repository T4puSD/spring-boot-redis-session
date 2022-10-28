package com.tapusd.redissessiondemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager authenticationManager() {
        final UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("password").roles("ADMIN", "USER").build();

        final UserDetails user = User.withDefaultPasswordEncoder()
                .username("user").password("password").roles("USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
                .and()
                .authorizeHttpRequests(httpRequest -> httpRequest
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .build();
    }
}
