-- liquibase formatted sql

-- changeset r.pereira:1732830871065-1
CREATE TABLE system_config
(
    cfg_id    BIGINT      NOT NULL,
    cfg_param VARCHAR(255) NOT NULL,
    cfg_value VARCHAR(255) NOT NULL,
    CONSTRAINT pk_system_config PRIMARY KEY (cfg_id)
);

-- changeset r.pereira:1732830871065-2
CREATE TABLE todo
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    subject     VARCHAR(255),
    description VARCHAR(255),
    user_login  VARCHAR,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    done        BOOLEAN,
    CONSTRAINT pk_todo PRIMARY KEY (id)
);

-- changeset r.pereira:1732830871065-3
CREATE TABLE "user"
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login VARCHAR(255)                             NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT uq_user_login UNIQUE (login)
);

-- changeset r.pereira:1732830871065-4
CREATE TABLE user_whatsapp
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id BIGINT,
    api_key VARCHAR(255),
    phone   VARCHAR(255),
    CONSTRAINT pk_user_whatsapp PRIMARY KEY (id)
);

-- changeset r.pereira:1732830871065-5
ALTER TABLE system_config
    ADD CONSTRAINT uc_system_config_cfg_param UNIQUE (cfg_param);

-- changeset r.pereira:1732830871065-6
ALTER TABLE user_whatsapp
    ADD CONSTRAINT uc_user_whatsapp_user UNIQUE (user_id);

-- changeset r.pereira:1732830871065-7
ALTER TABLE todo
    ADD CONSTRAINT FK_TODO_ON_USER_LOGIN FOREIGN KEY (user_login) REFERENCES "user" (login);

-- changeset r.pereira:1732830871065-8
ALTER TABLE user_whatsapp
    ADD CONSTRAINT FK_USER_WHATSAPP_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

