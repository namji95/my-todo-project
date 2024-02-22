package com.namji.mytodo.service;

import com.namji.mytodo.dto.user.requestDto.SignupRequestDto;
import com.namji.mytodo.entity.User;
import com.namji.mytodo.repository.UserRepository;
import com.namji.mytodo.util.JwtUtil;
import com.namji.mytodo.util.PasswordUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordUtil passwordUtil;
  private final JwtUtil jwtUtil;

  public void signup(SignupRequestDto requestDto) {
    // 회원가입 시 중복이 허용되면 안되는 값들을 전부 DB에서 조회 후 비교하여 중복 여부를 판단
    // 회원 중복 체크
    String username = requestDto.getUsername();
    Optional<User> checkUsername = userRepository.findByUsername(username);
    if (checkUsername.isPresent()) {
      throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
    }

    // 받아온 비밀번호 암호화
    String password = passwordUtil.passwordEncoder().encode(requestDto.getPassword());

    // email 중복 체크
    String email = requestDto.getEmail();
    Optional<User> checkEmail = userRepository.findByEmail(email);
    if (checkEmail.isPresent()) {
      throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
    }

    String nickname = requestDto.getUsername();

    User user = new User(username, password, email, nickname);

    userRepository.save(user);
  }
}
