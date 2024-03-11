package com.namji.mytodo.global.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    // 비밀번호를 암호화 해주는 해시 함수 : BCrypt
  }
}
