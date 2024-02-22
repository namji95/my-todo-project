package com.namji.mytodo.dto.todo.responseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TodoResponseDto(String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
