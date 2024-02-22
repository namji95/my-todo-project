package com.namji.mytodo.controller;

import com.namji.mytodo.dto.user.requestDto.SignupRequestDto;
import com.namji.mytodo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
  public String signup(
      @RequestBody SignupRequestDto requestDto, BindingResult result) {
    // todo: 강의는 @Valid를 사용하는데 값이 들어오지 않는 이유 @RequestBody를 사용하면 되는 이유
    List<FieldError> fieldErrors = result.getFieldErrors();
    if (!fieldErrors.isEmpty()) {
      for (FieldError fieldError : result.getFieldErrors()) {
        log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
      }
      return "회원가입도중 에러가 발생했습니다.";
    }
    // 회원가입도중 발생할 에러를 출력합니다.

    userService.signup(requestDto);
    // 회원가입을 위해 입력한 정보를 서비스 클래스의 signup 메서드에 보냄

    return "회원가입 성공";
  }
}
