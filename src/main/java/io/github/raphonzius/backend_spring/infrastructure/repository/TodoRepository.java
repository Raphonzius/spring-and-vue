package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, BigInteger> {

    List<Todo> findByDoneFalse();

    List<Todo> findByDoneFalseAndDueDateBetween(Date dueDateStart, Date dueDateEnd);

    @Override
    boolean existsById(BigInteger bigInteger);
}