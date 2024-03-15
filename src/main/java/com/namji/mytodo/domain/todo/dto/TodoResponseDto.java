package com.namji.mytodo.domain.todo.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TodoResponseDto {

  private final Long UserId;
  private final String title;
  private final String content;

  public TodoResponseDto(Long userId, String title, String contents) {
    this.UserId = userId;
    this.title = title;
    this.content = contents;
  }
}
