package io.github.raphonzius.backend_spring.api.v1.controller;

import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import io.github.raphonzius.backend_spring.infrastructure.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TodoController {

    private final TodoRepository todoRepository;

    @PostMapping("todo")
    public List<Todo> fetchTodos(){
        return todoRepository.findAll();
    }

}
