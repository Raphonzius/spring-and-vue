package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.TodoAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoAlertRepository extends JpaRepository<TodoAlert, BigInteger> {

    List<TodoAlert> findByAlertDateAndTodo_DeletedFalseAndAlertedFalse(LocalDateTime alertDate);

}