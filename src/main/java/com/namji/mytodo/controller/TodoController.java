package com.namji.mytodo.controller;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import com.namji.mytodo.dto.todo.responseDto.TodoResponseDto;
import com.namji.mytodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    // 할일 생성
    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> createTodo (@RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.createTodo(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
