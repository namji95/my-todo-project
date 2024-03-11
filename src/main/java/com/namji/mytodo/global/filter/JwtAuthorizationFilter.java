package com.namji.mytodo.global.filter;

import com.namji.mytodo.global.security.UserDetailsServiceImpl;
import com.namji.mytodo.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    String tokenValue = jwtUtil.getJwtFromHeader(request);
    // tokenValue는 순수한 토큰 받아옴

    if (StringUtils.hasText(tokenValue)) {
      log.info(tokenValue);
      if (!jwtUtil.validateToken(tokenValue)) {
        // 토큰에 있는 예외처리 메서드로 순수 토큰을 보내서 검사
        log.error("Token Error");
        return;
      }
      // 받아온 순수한 토큰 문제 없는지 검사

      Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

      try {
        setAuthentication(info.getSubject());
      } catch (Exception e) {
        log.error(e.getMessage());
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  // 인증 처리
  public void setAuthentication(String username) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(username);
    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }

  // 인증 객체 생성
  private Authentication createAuthentication(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        null
    );
  }
}
