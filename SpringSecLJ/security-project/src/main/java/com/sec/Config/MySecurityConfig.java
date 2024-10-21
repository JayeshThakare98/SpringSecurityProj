package com.sec.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    // Configuring in-memory authentication
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        User.UserBuilder userBuilder = User.withUsername("john")
                .password(passwordEncoder().encode("1234"))  // Password encoded
                .roles("NORMAL");

        userDetailsService.createUser(userBuilder.build());
        return userDetailsService;
    }

    // Password encoder bean to encode the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuring security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()  // All requests require authentication
                )
                .formLogin(withDefaults())  // Enable form-based login
                .httpBasic(withDefaults());  // Enable basic HTTP authentication

        return http.build();
    }
}
// <-------------------------------normal Authentication------------------------------->

//@Configuration
//public class MySecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//}
