package com.arthursouza.swiftflowMVP.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.arthursouza.swiftflowMVP.security.JWTAuthenticationFilter;
import com.arthursouza.swiftflowMVP.security.JWTAuthorizationFilter;
import com.arthursouza.swiftflowMVP.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Autowired
    private JWTUtil jwtUtil;


    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;



    public static final String[] PUBLIC_MATCHERS = {

            "/"

    };

    public static final String[] PUBLIC_MATCHERS_POST = {

            "/user",
            "/login"

    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // Disable the "csrf" protection, usable for test the program
        http.cors().and().csrf().disable();


        AuthenticationManagerBuilder authenticationManagerBuilder = http
            .getSharedObject(AuthenticationManagerBuilder.class);

        
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder());


        this.authenticationManager = authenticationManagerBuilder.build();



        // Disable the process of authentication from the following endpoints
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .requestMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated().and()
                .authenticationManager(authenticationManager);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new JWTAuthenticationFilter(this.authenticationManager, this.jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(this.authenticationManager, this.jwtUtil, this.userDetailsService));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

}