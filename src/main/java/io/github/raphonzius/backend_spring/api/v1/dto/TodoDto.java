package io.github.raphonzius.backend_spring.api.v1.dto;

import io.github.raphonzius.backend_spring.core.model.entity.Todo;
import io.github.raphonzius.backend_spring.core.model.entity.User;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO for {@link Todo}
 */
@Builder
@Value
public class TodoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    String subject;
    String description;
    String userLogin;
    Date createDate;
    Date dueDate;
    Boolean done;

}