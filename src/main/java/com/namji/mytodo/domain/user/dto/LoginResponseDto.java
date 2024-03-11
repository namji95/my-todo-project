package com.namji.mytodo.domain.user.dto;

import com.namji.mytodo.domain.user.dto.LoginRequestDto;
import lombok.Getter;

@Getter
public class LoginResponseDto {

  private String username;
  private String password;
  private String token;

  public LoginResponseDto(LoginRequestDto requestDto, String token) {
    this.username = requestDto.getUsername();
    this.password = requestDto.getPassword();
    this.token = token;
  }
}
