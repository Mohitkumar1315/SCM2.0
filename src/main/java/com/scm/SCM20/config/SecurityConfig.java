package com.scm.SCM20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.SCM20.services.Impl.SecurityCustomeDetailsService;
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
    
    @Autowired
    private SecurityCustomeDetailsService uSecurityCustomeDetailsService;
    //Configration of authentication provider spring security
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
       //UserServiceDetails object creation
        daoAuthenticationProvider.setUserDetailsService(uSecurityCustomeDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;     
         
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    //Configure of Spring Security Fileter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        //URL configuration :public or private
        httpSecurity.authorizeHttpRequests(authorize->{
          //authorize.requestMatchers("/home","register","images/telephone.png").permitAll();  
          authorize.requestMatchers("/user/**").authenticated();
          authorize.anyRequest().permitAll();
        });
        //form defaul login 
            //if we want to need changes related to form login then we need to change here 
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
        
    }
}
