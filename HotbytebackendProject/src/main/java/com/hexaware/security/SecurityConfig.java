package com.hexaware.security;

import com.hexaware.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .cors().and()
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Prefight - Allows the permit all
                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                .requestMatchers("/api/customer/register").permitAll()
                .requestMatchers("/api/restaurant/register").permitAll()
                .requestMatchers("/api/menu/menu", "/api/menu/menus", "/api/menu/filter").permitAll()
                .requestMatchers("/api/category/get").permitAll()
                .requestMatchers("/api/restaurant/all").permitAll()
                .requestMatchers("/api/customer/**").hasAuthority("ROLE_CUSTOMER")
                .requestMatchers("/api/cart/**").hasAuthority("ROLE_CUSTOMER")
                .requestMatchers("/api/order/orders").hasAnyAuthority("ROLE_ADMIN","ROLE_RESTAURANT")
                .requestMatchers("/api/order/**").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_RESTAURANT")
                .requestMatchers("/api/payment/**").hasAuthority("ROLE_CUSTOMER")
                .requestMatchers("api/restaurant/{restaurantId}/deactivate","api/restaurant/{restaurantId}/activate").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/restaurant/**").hasAuthority("ROLE_RESTAURANT")
                .requestMatchers("/api/category/{id}/activate","/api/category/{id}/deactivate").hasAnyAuthority("ROLE_RESTAURANT","ROLE_ADMIN")
                .requestMatchers("/api/category/**").hasAnyAuthority("ROLE_RESTAURANT","ROLE_ADMIN")
                .requestMatchers("/api/menu/**").permitAll()	//hasAuthority("ROLE_RESTAURANT")
                .requestMatchers("/api/users/**").permitAll()//hasAnyAuthority("ROLE_CUSTOMER","ROLE_RESTAURANT","ROLE_ADMIN")
                .requestMatchers("/api/coupons/**").permitAll()
                .requestMatchers("/api/auth/request-reset").permitAll()
                .requestMatchers("/api/auth/reset-password").permitAll()
                .requestMatchers("/api/contact/send").permitAll()
//                .requestMatchers("/api/order/placeorder").permitAll()//
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
