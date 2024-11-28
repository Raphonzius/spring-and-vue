package io.github.raphonzius.backend_spring.core.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Getter
@Entity
@Table(name = "SYSTEM_CONFIG")
public class SystemConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger cfgId;

    @Column(name = "CFG_PARAM", unique = true, nullable = false)
    private String cfgParam;

    @Column(name = "CFG_VALUE")
    private String cfgValue;

}
