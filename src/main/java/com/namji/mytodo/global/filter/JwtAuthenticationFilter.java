package com.namji.mytodo.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namji.mytodo.domain.user.dto.LoginRequestDto;
import com.namji.mytodo.global.security.UserDetailsImpl;
import com.namji.mytodo.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtUtil jwtUtil;

  public JwtAuthenticationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws AuthenticationException {
    log.info("로그인 시도");
    try {
      LoginRequestDto requestDto = new ObjectMapper()
          .readValue(request.getInputStream(), LoginRequestDto.class);
      // LoginRequestDto를 Controller에 보내지기 전에 여기서 받아서 사용

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              requestDto.getUsername(),
              requestDto.getPassword(),
              null
          )
      );
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authentication
  ) throws IOException, ServletException {
    log.info("로그인 성공 및 JWT 토큰 생성");
    String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();

    String token = jwtUtil.createToken(username);
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }

  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException failed
  ) throws IOException, ServletException {
    log.info("로그인 실패");
    response.setStatus(401);
  }
}
