package com.namji.mytodo.dto.user.requestDto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

  private String username;
  private String password;
  private String email;
  private String nickname;
}
