package com.namji.mytodo.domain.user.service;

import com.namji.mytodo.domain.user.dto.LoginRequestDto;
import com.namji.mytodo.domain.user.dto.SignupRequestDto;
import com.namji.mytodo.domain.user.dto.LoginResponseDto;
import com.namji.mytodo.domain.user.dto.SignupResponseDto;
import com.namji.mytodo.domain.user.entity.User;
import com.namji.mytodo.domain.user.repository.UserRepository;
import com.namji.mytodo.global.util.JwtUtil;
import com.namji.mytodo.global.util.PasswordUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordUtil passwordUtil;
  private final JwtUtil jwtUtil;

  public SignupResponseDto signup(SignupRequestDto requestDto) {
    // 회원가입 시 중복이 허용되면 안되는 값들을 전부 DB에서 조회 후 비교하여 중복 여부를 판단
    // 회원 중복 체크
    String username = requestDto.getUsername();
    Optional<User> checkUsername = userRepository.findByUsername(username);
    if (checkUsername.isPresent()) {
      throw new IllegalArgumentException("아이디를 입력하세요");
    }

    // 받아온 비밀번호 암호화
    String password = passwordUtil.passwordEncoder().encode(requestDto.getPassword());
    if (password == null) {
      throw new IllegalArgumentException("비밀번호를 입력하세요");
    }

    // email 중복 체크
    String email = requestDto.getEmail();
    Optional<User> checkEmail = userRepository.findByEmail(email);
    if (checkEmail.isPresent()) {
      throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
    }

    String nickname = requestDto.getUsername();

    User user = new User(username, password, email, nickname);

    userRepository.save(user);

    return new SignupResponseDto(
        user.getUsername(),
        user.getPassword(),
        user.getEmail(),
        user.getNickname()
    );
  }

  public LoginResponseDto login(LoginRequestDto requestDto) {
    User user = findUser(requestDto.getUsername());
    if (user == null) {
      throw new IllegalArgumentException("존재하지 않는 회원입니다!!!");
    }
    if (!passwordUtil.passwordEncoder().matches(requestDto.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
    String token = jwtUtil.createToken(user.getUsername());
    LoginResponseDto responseDto = new LoginResponseDto(requestDto, token);
    return responseDto;
  }

  private User findUser(String username) {
    return userRepository.findByUsername(username).orElseThrow(()
        -> new IllegalCallerException("존재하지 않는 회원입니다."));
  }


}
