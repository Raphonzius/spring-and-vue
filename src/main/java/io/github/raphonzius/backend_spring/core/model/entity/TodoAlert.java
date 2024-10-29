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
@Table(name = "TODO_ALERT")
public class TodoAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "TODO_ID", referencedColumnName = "ID")
    private Todo todo;

    @Column(name = "ALERT_DATE")
    private LocalDateTime alertDate;

    @Column(name = "ALERTED")
    private Boolean alerted;

}
