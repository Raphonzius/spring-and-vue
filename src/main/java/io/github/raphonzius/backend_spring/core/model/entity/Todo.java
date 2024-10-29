package io.github.raphonzius.backend_spring.core.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @Column(name = "DONE")
    private boolean done;

    @Column(name = "DELETED")
    private boolean deleted;

}
