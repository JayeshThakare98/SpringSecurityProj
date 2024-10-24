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
    // Configuring in-memory authentication with multiple users
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // User with role NORMAL
        User.UserBuilder user1 = User.withUsername("john")
                .password(passwordEncoder().encode("1234"))
                .roles("NORMAL");   

        // User with role ADMIN
        User.UserBuilder user2 = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN");

        // User with both NORMAL and ADMIN roles
        User.UserBuilder user3 = User.withUsername("superuser")
                .password(passwordEncoder().encode("super123"))
                .roles("NORMAL", "ADMIN");

        // Add users to the in-memory user details manager
        userDetailsService.createUser(user1.build());
        userDetailsService.createUser(user2.build());
        userDetailsService.createUser(user3.build());

        return userDetailsService;
    }

    // Password encoder bean to encode the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // <-------------------------------for Form-Based Authentication------------------------------->
    // Configuring security filter chain with form-based authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for testing; enable it in production
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access admin endpoints
                        .requestMatchers("/user/**").hasAnyRole("NORMAL", "ADMIN")  // Both NORMAL and ADMIN can access user endpoints
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Specify the custom login page (optional)
                        .permitAll()  // Allow everyone to access the login page
                        .defaultSuccessUrl("/home", true)  // Redirect to the home page on successful login
                        .failureUrl("/login?error=true")  // Redirect to login page on failure
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Specify the logout URL
                        .logoutSuccessUrl("/login?logout=true")  // Redirect to login page after logout
                        .permitAll()
                )
                .httpBasic(withDefaults());  // Enable basic HTTP authentication (optional, for API calls)

        return http.build();
    }
  // <-------------------------------for normal Authentication with csir------------------------------->
//    // Configuring security filter chain
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**").hasRole("NORMAL")  // Only ADMIN can access admin endpoints
//                                .requestMatchers("/user/**").hasRole("ADMIN")
////                        .requestMatchers("/user/**").hasAnyRole("NORMAL", "ADMIN")  // Both NORMAL and ADMIN can access user endpoints
//                        .anyRequest().authenticated()  // All other requests require authentication
//                )
//                .formLogin(withDefaults())  // Enable form-based login
//                .httpBasic(withDefaults());  // Enable basic HTTP authentication
//
//        return http.build();
//    }
}


/*
    // <-------------------------------for Single User------------------------------->
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
*/
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
