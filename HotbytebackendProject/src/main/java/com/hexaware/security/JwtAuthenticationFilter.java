package com.hexaware.security;

  import jakarta.servlet.FilterChain;
  import jakarta.servlet.ServletException;
  import jakarta.servlet.http.HttpServletRequest;
  import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
  import org.springframework.security.core.context.SecurityContextHolder;
  import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
  import org.springframework.stereotype.Component;
  import org.springframework.web.filter.OncePerRequestFilter;

  import com.hexaware.util.JwtUtil;
  import io.jsonwebtoken.ExpiredJwtException;

  @Component
  public class JwtAuthenticationFilter extends OncePerRequestFilter {
	  
	  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
      @Autowired
      private CustomUserDetailsService userDetailsService;

      @Autowired
      private JwtUtil jwtUtil;

//      @Override
//      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//              throws ServletException, IOException {
//          String header = request.getHeader("Authorization");
//          String username = null;
//          String jwt = null;
//
//          if (header != null && header.startsWith("Bearer ")) {
//              jwt = header.substring(7);
//              try {
//                  username = jwtUtil.extractUsername(jwt);
//              } catch (ExpiredJwtException e) {
//                  System.out.println("JWT Token has expired");
//              } catch (Exception e) {
//                  System.out.println("Invalid JWT Token");
//              }
//          }
//
//          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//              if (jwtUtil.validateToken(jwt)) {
//                  UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                          userDetails, null, userDetails.getAuthorities());
//                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//              }
//          }
//          chain.doFilter(request, response);
//      }
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
              throws ServletException, IOException {
          String header = request.getHeader("Authorization");
          String username = null;
          String jwt = null;

          if (header != null && header.startsWith("Bearer ")) {
              jwt = header.substring(7);
              try {
                  username = jwtUtil.extractUsername(jwt);
                  logger.debug("Extracted username from JWT: {}", username);
              } catch (ExpiredJwtException e) {
                  logger.error("JWT token has expired: {}", e.getMessage());
              } catch (Exception e) {
                  logger.error("Invalid JWT token (signature verification failed): {}", e.getMessage());
              }
          } else {
              logger.debug("No JWT token found in request headers");
          }

          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
              if (jwtUtil.validateToken(jwt)) {
                  UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetails, null, userDetails.getAuthorities());
                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                  logger.info("Authenticated user: {}, Authorities: {}", username, userDetails.getAuthorities());
              } else {
                  logger.warn("JWT token validation failed for user: {}", username);
              }
          }

          chain.doFilter(request, response);
      }
  }