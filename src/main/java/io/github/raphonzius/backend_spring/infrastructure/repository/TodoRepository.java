package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, BigInteger> {

    List<Todo> findByUser_IdAndDoneFalseAndDeletedFalse(BigInteger id);

}