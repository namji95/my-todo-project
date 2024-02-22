package com.namji.mytodo.security;

import com.namji.mytodo.entity.User;
import com.namji.mytodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  // UserDetailsService는 사용자 인증 처리하는 클래스 Spring Security에서 사용자 인증 정보를 로드하는데 사용

  private final UserRepository userRepository;
  // UserRepository 객체 생성 후 생성자를 통해 UserRepository 객체를 받아서 멤버 변수로 저장

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("NOT FOUND" + username));
    return new UserDetailsImpl(user);
  }
}
