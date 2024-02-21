package com.namji.mytodo.service;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import com.namji.mytodo.dto.todo.responseDto.TodoResponseDto;
import com.namji.mytodo.entity.Todo;
import com.namji.mytodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할일 생성 로직
    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);

        todoRepository.save(todo);

        return new TodoResponseDto(todo.getTitle(), todo.getContents());
    }

    // 할일 전체 조회 로직
    @Transactional(readOnly = true)
    public List<Todo> getTodoList() {
        return todoRepository.findAll();
        // todo: 전체 조회에서 엔티티를 반환하지 않는 방법? ResponseDto 사용해서 반환하는 방법
    }

    // 할일 선택 조회 로직
    @Transactional(readOnly = true)
    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = findTodo(todoId);
        TodoResponseDto responseDto = new TodoResponseDto(todo.getTitle(), todo.getContents());
        return responseDto;
    }

    // 할일 수정 로직
    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto requestDto) {
        Todo todo = findTodo(todoId);
        todo.updateTodo(requestDto);
        // jpa 중요한 요소 더티체킹

        // todoRepository.save(todo);
        // jpa에는 write query 트랜젝셔널이 같은 동작이 들어오면 쓰기 지연
        // 더티체킹과 쓰기 지연 중요 ***

        TodoResponseDto responseDto = new TodoResponseDto(todo.getTitle(), todo.getContents());

        return responseDto;
    }

    // 할일 삭제 로직
    @Transactional
    public TodoResponseDto deleteTodo(Long todoId) {
        Todo todo = findTodo(todoId);
        todoRepository.delete(todo);

        return new TodoResponseDto(todo.getTitle(), todo.getContents());
    }

    private Todo findTodo (Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(()
                -> new NullPointerException("선택한 정보는 없는 정보입니다."));
    }
}
