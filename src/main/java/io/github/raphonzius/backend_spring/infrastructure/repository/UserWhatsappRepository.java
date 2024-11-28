package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.UserWhatsapp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserWhatsappRepository extends JpaRepository<UserWhatsapp, BigInteger> {

  UserWhatsapp findByUser_LoginIgnoreCase(String login);

}