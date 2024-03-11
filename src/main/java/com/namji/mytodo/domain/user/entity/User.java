package com.namji.mytodo.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotBlank
  @Column(unique = true, length = 50)
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  @Column(unique = true)
  private String email;

  @NotBlank(message = "닉네임은 필수 입력값입니다.")
  @Column(length = 100)
  private String nickname;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime modifiedAt;

  public User(String username, String password, String email, String nickname) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.nickname = nickname;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }
}
