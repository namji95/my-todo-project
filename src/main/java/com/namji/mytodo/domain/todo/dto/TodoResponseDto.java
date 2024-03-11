package com.namji.mytodo.domain.todo.dto;

import com.namji.mytodo.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TodoResponseDto {

  private User user;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public TodoResponseDto(
      User user, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.user = user;
    this.title = title;
    this.content = contents;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }
}
