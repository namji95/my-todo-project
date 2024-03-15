package com.namji.mytodo.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

  private final String userId;
  private final String password;
  private final String token;

  public LoginResponseDto(LoginRequestDto requestDto, String token) {
    this.userId = requestDto.getUserId();
    this.password = requestDto.getPassword();
    this.token = token;
  }
}
