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
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoService todoService;

    // 할일 생성
    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> createTodo (
            @RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.createTodo(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // ResponseEntity
    }

    // 할일 전체 조회
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodoList () {
        return new ResponseEntity<>(todoService.getTodoList(), HttpStatus.OK);
    }

    // 할일 선택 조회
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo (
            @PathVariable Long todoId) {
        TodoResponseDto responseDto = todoService.getTodo(todoId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 할일 수정
    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo (
            @PathVariable Long todoId,
            @RequestBody TodoRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.updateTodo(todoId, requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    // 할일 삭제
    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> deleteTodo (
            @PathVariable Long todoId) {
        TodoResponseDto responseDto = todoService.deleteTodo(todoId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
