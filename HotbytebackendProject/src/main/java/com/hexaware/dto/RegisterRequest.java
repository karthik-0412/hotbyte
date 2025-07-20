package com.hexaware.dto;

  import com.hexaware.enums.UserRole;
  import lombok.Data;

  @Data
  public class RegisterRequest {
      private String username;
      private String password;
      private UserRole role;
  }