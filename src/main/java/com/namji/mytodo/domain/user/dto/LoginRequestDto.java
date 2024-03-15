package com.namji.mytodo.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

  @NotBlank(message = "유저 아이디는 빈 값 또는 공백만 입력할 수 없습니다.")
  //  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,10}$",
//      message = "아이디는 특수문자를 제외한 5자 이상이어야 합니다.")
  private String userId;

  @NotBlank(message = "유저 비밀번호는 빈 값 또는 공백만 입력할 수 없습니다.")
  //  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",
//      message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
  private String password;
}
