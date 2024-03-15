package com.namji.mytodo.global.security;

import com.namji.mytodo.domain.user.entity.User;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

  // UserDetails 인터페이스는 Spring Security에서 사용자의 정보를 나타내는데 사용합니다.
  private final User user;

  // User 객체 생성 후 생성자를 통해 이 객체를 받아서 멤버 변수로 사용

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // 사용자 권한 설정 시 사용
    return null;
  }

  @Override
  public String getPassword() {
    // UserDetails 메서드 오버라이드 하여 사용자 비밀번호 반환
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    // UserDetails 메서드 오버라이드 하여 사용자 이름 (아이디) 정보 반환
    return user.getUserId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
