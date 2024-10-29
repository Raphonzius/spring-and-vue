package io.github.raphonzius.backend_spring.api.v1.controller;

import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import io.github.raphonzius.backend_spring.infrastructure.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/todo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping
    public List<Todo> fetchAllTodos(){
        return todoRepository.findAll();
    }

    @PostMapping("{userId}")
    public List<Todo> fetchTodosByUser(BigInteger userId){
        return todoRepository.findByUser_IdAndDoneFalseAndDeletedFalse(userId);
    }


}
