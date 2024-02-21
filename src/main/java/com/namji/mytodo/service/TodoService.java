package com.namji.mytodo.service;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import com.namji.mytodo.dto.todo.responseDto.TodoResponseDto;
import com.namji.mytodo.entity.Todo;
import com.namji.mytodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할일 생성 로직
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        // todo: 유저가 생기면 유저 정보로 할일 체크한 후 저장할 수 있게 수정
        todoRepository.save(todo);

        return new TodoResponseDto(todo.getTitle(), todo.getContents());
        // todo: DB의 값을 가져와서 반환하는게 아닌 내가 입력한 값을 반환 -> 저장하고 DB 값 가져오는 방법은?
    }

    // 할일 전체 조회 로직
    public List<Todo> getTodoList() {
        return todoRepository.findAll();
        // todo: 전체 조회에서 엔티티를 반환하지 않는 방법? ResponseDto 사용해서 반환하는 방법
    }

    // 할일 선택 조회 로직
    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()
                -> new NullPointerException("선택된 정보는 없는 정보입니다."));
        TodoResponseDto responseDto = new TodoResponseDto(todo.getTitle(), todo.getContents());
        return responseDto;
    }
}
