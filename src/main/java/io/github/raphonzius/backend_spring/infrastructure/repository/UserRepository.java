package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    User findByLogin(String login);

    boolean existsByLogin(String login);

}
