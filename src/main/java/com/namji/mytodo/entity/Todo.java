package com.namji.mytodo.entity;

import com.namji.mytodo.dto.todo.requestDto.TodoRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@Table(name = "todos")
@NoArgsConstructor
public class Todo {

  // 일정관리 테이블에 필요한 컬럼은?
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long todoId;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  @Column(nullable = false)
  private String contents;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime modifiedAt;

  public Todo(TodoRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContent();
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }

  public void updateTodo(TodoRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.contents = requestDto.getContent();
    this.modifiedAt = LocalDateTime.now();
  }
}
