package io.github.raphonzius.backend_spring.api.v1.controller;

import io.github.raphonzius.backend_spring.api.v1.dto.TodoDto;
import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import io.github.raphonzius.backend_spring.infrastructure.repository.TodoRepository;
import io.github.raphonzius.backend_spring.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/todo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Todo> fetchPendingTodos() {
        return todoRepository.findByDoneFalse();
    }

    @PostMapping("new")
    public ResponseEntity<Todo> newTodo(@RequestBody TodoDto todoDto) {
        if (todoDto == null && !userRepository.existsByLogin(todoDto.getUserLogin())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Todo todo = todoRepository.save(
                Todo.builder()
                        .subject(todoDto.getSubject())
                        .description(todoDto.getDescription())
                        .user(userRepository.findByLogin(todoDto.getUserLogin()))
                        .createDate(todoDto.getCreateDate())
                        .dueDate(todoDto.getDueDate())
                        .done(todoDto.getDone())
                        .build());

        return ResponseEntity.status(HttpStatus.CREATED).body(todo);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestAttribute BigInteger id, @RequestBody TodoDto todoDto) {
        if (!todoRepository.existsById(id) && todoDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Todo> optTodo = todoRepository.findById(id);
        if (optTodo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Todo todo = optTodo.get();

        todo.setSubject(todoDto.getSubject());
        todo.setDescription(todoDto.getDescription());
        todo.setUser(userRepository.findByLogin(todoDto.getUserLogin()));
        todo.setCreateDate(todoDto.getCreateDate());
        todo.setDueDate(todoDto.getDueDate());
        todo.setDone(todoDto.getDone());

        todoRepository.save(todo);

        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTodo(@RequestAttribute BigInteger id) {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Todo> optTodo = todoRepository.findById(id);
        if (optTodo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Todo todo = optTodo.get();
        todoRepository.delete(todo);

        return ResponseEntity.status(HttpStatus.OK).body(todo + "deleted successfully");
    }

}
