package com.namji.mytodo.domain.user.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {

  private String username;
  private String password;
  private String email;
  private String nickname;

  public SignupResponseDto(String username, String password, String email, String nickname) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
  }
}
