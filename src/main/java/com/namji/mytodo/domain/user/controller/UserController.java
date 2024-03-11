package com.namji.mytodo.domain.user.controller;

import com.namji.mytodo.domain.user.dto.LoginRequestDto;
import com.namji.mytodo.domain.user.dto.SignupRequestDto;
import com.namji.mytodo.domain.user.dto.LoginResponseDto;
import com.namji.mytodo.domain.user.dto.SignupResponseDto;
import com.namji.mytodo.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

  private final UserService userService;

  @PostMapping("/users/signup")
  public SignupResponseDto signup(
      @Valid @RequestBody SignupRequestDto requestDto) {
//    List<FieldError> errors = result.getFieldErrors();
//    if (!errors.isEmpty()) {
//      for (FieldError fieldError : errors) {
//        log.error(fieldError.getField() + " 필드" + fieldError.getDefaultMessage());
//      }
//    }

    SignupResponseDto responseDto = userService.signup(requestDto);
    // 회원가입을 위해 입력한 정보를 서비스 클래스의 signup 메서드에 보냄

    return responseDto;
  }

  @PostMapping("/users/login")
  public LoginResponseDto login(
      @Valid @RequestBody LoginRequestDto requestDto) {
    LoginResponseDto responseDto = userService.login(requestDto);

    return responseDto;
  }
}
