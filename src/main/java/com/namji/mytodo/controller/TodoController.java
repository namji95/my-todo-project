package com.namji.mytodo.controller;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import com.namji.mytodo.dto.todo.responseDto.TodoResponseDto;
import com.namji.mytodo.entity.Todo;
import com.namji.mytodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 할일 전체 조회
    @GetMapping("/todoList")
    public ResponseEntity<List<Todo>> getTodoList() {
        List<Todo> todoList = todoService.getTodoList();

        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }
}
