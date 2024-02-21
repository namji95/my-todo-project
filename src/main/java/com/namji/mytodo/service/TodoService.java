package com.namji.mytodo.service;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import com.namji.mytodo.dto.todo.responseDto.TodoResponseDto;
import com.namji.mytodo.entity.Todo;
import com.namji.mytodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        // todo: 유저가 생기면 유저 정보로 할일 체크한 후 저장할 수 있게 수정
        todoRepository.save(todo);

        return new TodoResponseDto(todo.getTitle(), todo.getContents());
        // todo: DB의 값을 가져와서 반환하는게 아닌 내가 입력한 값을 반환 -> 저장하고 DB 값 가져오는 방법은?
    }
}
