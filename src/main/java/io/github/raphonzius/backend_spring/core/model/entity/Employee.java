package io.github.raphonzius.backend_spring.core.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "EMPLOYEE_FIRST_NAME", nullable = false)
    private String employeeFirstName;

    @Column(name = "EMPLOYEE_LAST_NAME")
    private String employeeLastName;

    @Column(name = "EMPLOYEE_EMAIL", nullable = false)
    private String employeeEmail;

}
