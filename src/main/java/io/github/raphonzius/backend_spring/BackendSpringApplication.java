package io.github.raphonzius.backend_spring;

import io.github.raphonzius.backend_spring.core.model.entity.Employee;
import io.github.raphonzius.backend_spring.infrastructure.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {

		Employee e1 = Employee.builder()
				.employeeFirstName("John")
				.employeeLastName("Doe 1")
				.employeeEmail("johndoe@email.com")
				.build();

		Employee e2 = Employee.builder()
				.employeeFirstName("John")
				.employeeLastName("Doe 2")
				.employeeEmail("johndoe@email.com")
				.build();

		Employee e3 = Employee.builder()
				.employeeFirstName("John")
				.employeeLastName("Doe 3")
				.employeeEmail("johndoe@email.com")
				.build();

		employeeRepository.save(e1);
		employeeRepository.save(e2);
		employeeRepository.save(e3);

	}
}
