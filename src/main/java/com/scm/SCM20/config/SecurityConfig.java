package com.scm.SCM20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.scm.SCM20.services.Impl.SecurityCustomeDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private OAuthenicationSuccessHandler oAuthenicationSuccessHandler;
    // User create and login using java code with in-memorey authetication
    // create default user using spring security
    // UserDetails u = User
    // .withDefaultPasswordEncoder()
    // .username("chinku")
    // .roles("ADMIN,USER")
    // .password("mvkkabir")
    // .build();
    // UserDetails user =
    // User.withUsername("mohit").password("Mvkkabir").roles("ADMIN").build();
    // UserDetails user1 =
    // User.withUsername("chinki").password("Mvkkabir").roles("USER").build();

    // @Bean
    // public UserDetailsService userDetailsService() {
    // return new InMemoryUserDetailsManager(user, user1, u);
    // } //for use in-memory authentication you can cmmt all below :

    @Autowired
    private SecurityCustomeDetailsService uSecurityCustomeDetailsService;

    // Configration of authentication provider spring security
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // UserServiceDetails object creation
        daoAuthenticationProvider.setUserDetailsService(uSecurityCustomeDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure of Spring Security Fileter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // URL configuration :public or private
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home","register","images/telephone.png").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            // authorize.requestMatchers("/login").authenticated();//this is for test just
            // only
            authorize.anyRequest().permitAll();
        });
        // form defaul login
        // if we want to need changes related to form login then we need to change here
        // httpSecurity.formLogin(Customizer.withDefaults());//this is use for defaul
        // configuration
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .successForwardUrl("/user/dashboard")
                    // .failureForwardUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password");
        });
        httpSecurity.logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll();
        });
        // oauth configurations

    //    httpSecurity.oauth2Login(Customizer.withDefaults());  //this is for defaultl configuration
        //now we are do oauth config again using login form  
       httpSecurity.oauth2Login(oauth->{
        oauth.loginPage("/login");
        oauth.successHandler(oAuthenicationSuccessHandler);

       });
        return httpSecurity.build();

    }
}
