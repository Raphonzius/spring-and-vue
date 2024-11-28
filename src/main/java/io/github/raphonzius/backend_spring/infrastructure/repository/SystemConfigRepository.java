package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, BigInteger> {

  SystemConfig findByCfgParam(String cfgParam);

}