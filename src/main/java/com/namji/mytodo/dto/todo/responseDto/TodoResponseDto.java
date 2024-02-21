package com.namji.mytodo.dto.todo.responseDto;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private String title;
    private String content;

    public TodoResponseDto(String title, String contents) {
        this.title = title;
        this.content = contents;
    }
}
