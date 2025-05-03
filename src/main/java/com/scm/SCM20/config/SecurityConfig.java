package com.scm.SCM20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    // User create and login using java code with in-memorey authetication
    // create default user using spring security
    // UserDetails u = User
    //         .withDefaultPasswordEncoder()
    //         .username("chinku")
    //         .roles("ADMIN,USER")
    //         .password("mvkkabir")
    //         .build();
    // UserDetails user = User.withUsername("mohit").password("Mvkkabir").roles("ADMIN").build();
    // UserDetails user1 = User.withUsername("chinki").password("Mvkkabir").roles("USER").build();

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new InMemoryUserDetailsManager(user, user1, u);
    // }

}
