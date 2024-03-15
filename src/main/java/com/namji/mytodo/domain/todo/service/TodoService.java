package com.namji.mytodo.domain.todo.service;

import com.namji.mytodo.domain.todo.dto.TodoRequestDto;
import com.namji.mytodo.domain.todo.dto.TodoResponseDto;
import com.namji.mytodo.domain.todo.entity.Todo;
import com.namji.mytodo.domain.todo.repository.TodoRepository;
import com.namji.mytodo.domain.user.entity.User;
import com.namji.mytodo.global.security.UserDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ListModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;

  // 할일 생성 로직
  @Transactional
  public TodoResponseDto createTodo(
      TodoRequestDto requestDto,
      UserDetailsImpl userDetails) {
    Todo todo = new Todo(
        requestDto,
        userDetails.getUser().getId());

    todoRepository.save(todo);

    return new TodoResponseDto(
        todo.getUserId(),
        todo.getTitle(),
        todo.getContents());
  }

  // 할일 전체 조회 로직
  @Transactional(readOnly = true)
  public List<TodoResponseDto> getTodoList() {
    List<Todo> todos = todoRepository.findAll();
    List<TodoResponseDto> todoResponseDtos = todos.stream().map(
        todo -> new TodoResponseDto(
            todo.getUserId(), todo.getTitle(), todo.getContents())
        ).toList();
//    List<TodoResponseDto> todoResponseDtos = new ArrayList<>();
//
//    for (Todo todo : todoRepository.findAll()) {
//      todoResponseDtos.add(
//          new TodoResponseDto(
//              todo.getUserId(),
//              todo.getTitle(),
//              todo.getContents()
//          ));
//    }

    return todoResponseDtos;
    // todo: 전체 조회에서 엔티티를 반환하지 않는 방법? ResponseDto 사용해서 반환하는 방법
  }

  // 할일 선택 조회 로직
  @Transactional(readOnly = true)
  public TodoResponseDto getTodo(Long todoId) {
    Todo todo = findTodo(todoId);
    TodoResponseDto responseDto = new TodoResponseDto(
        todo.getUserId(),
        todo.getTitle(),
        todo.getContents());
    return responseDto;
  }

  // 할일 수정 로직
  @Transactional
  public TodoResponseDto updateTodo(
      Long todoId,
      TodoRequestDto requestDto,
      UserDetailsImpl userDetails) {
    Todo todo = findTodo(todoId);
    if (!todo.getUserId().equals(userDetails.getUser().getId())) {
      throw new IllegalArgumentException("본인이 작성한 일정만 수정할 수 있습니다.");
    }

    todo.updateTodo(requestDto);
    // jpa 중요한 요소 더티체킹
    // todoRepository.save(todo);
    // jpa에는 write query 트랜젝셔널이 같은 동작이 들어오면 쓰기 지연
    // 더티체킹과 쓰기 지연 중요 ***

    TodoResponseDto responseDto = new TodoResponseDto(
        todo.getUserId(),
        todo.getTitle(),
        todo.getContents());

    return responseDto;
  }

  // 할일 삭제 로직
  @Transactional
  public TodoResponseDto deleteTodo(
      Long todoId,
      UserDetailsImpl userDetails) {
    Todo todo = findTodo(todoId);
    if (!todo.getUserId().equals(userDetails.getUser().getId())) {
      throw new IllegalArgumentException("본인이 작성한 일정만 삭제할 수 있습니다.");
    }
    todoRepository.delete(todo);

    return new TodoResponseDto(
        todo.getUserId(),
        todo.getTitle(),
        todo.getContents());
  }

  private Todo findTodo(Long todoId) {
    return todoRepository.findById(todoId).orElseThrow(()
        -> new NullPointerException("선택한 정보는 없는 정보입니다."));
  }
}
