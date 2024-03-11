package com.namji.mytodo.domain.todo.repository;

import com.namji.mytodo.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
