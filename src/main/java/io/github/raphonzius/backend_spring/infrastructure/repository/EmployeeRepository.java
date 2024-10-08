package io.github.raphonzius.backend_spring.infrastructure.repository;

import io.github.raphonzius.backend_spring.core.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface EmployeeRepository extends JpaRepository<Employee,BigInteger> {

}
