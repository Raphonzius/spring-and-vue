-- liquibase formatted sql

-- changeset r.pereira:1730243249645-9
ALTER TABLE employee DROP CONSTRAINT fk_employee_on_company;

-- changeset r.pereira:1730243249645-1
CREATE TABLE todo
(
    id          DECIMAL GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    subject     VARCHAR(255),
    description VARCHAR(255),
    user_id     DECIMAL,
    create_date TIMESTAMP WITHOUT TIME ZONE,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    done        BOOLEAN,
    deleted     BOOLEAN,
    CONSTRAINT pk_todo PRIMARY KEY (id)
);

-- changeset r.pereira:1730243249645-2
CREATE TABLE todo_alert
(
    id         DECIMAL GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    todo_id    DECIMAL,
    alert_date TIMESTAMP WITHOUT TIME ZONE,
    alerted    BOOLEAN,
    CONSTRAINT pk_todo_alert PRIMARY KEY (id)
);

-- changeset r.pereira:1730243249645-3
CREATE TABLE "user"
(
    id       DECIMAL GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login    VARCHAR(255)                             NOT NULL,
    password VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- changeset r.pereira:1730243249645-4
CREATE TABLE user_whatsapp
(
    id      DECIMAL GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id DECIMAL,
    api_key VARCHAR(255),
    phone   VARCHAR(255),
    CONSTRAINT pk_user_whatsapp PRIMARY KEY (id)
);

-- changeset r.pereira:1730243249645-5
ALTER TABLE user_whatsapp
    ADD CONSTRAINT uc_user_whatsapp_user UNIQUE (user_id);

-- changeset r.pereira:1730243249645-6
ALTER TABLE todo_alert
    ADD CONSTRAINT FK_TODO_ALERT_ON_TODO FOREIGN KEY (todo_id) REFERENCES todo (id);

-- changeset r.pereira:1730243249645-7
ALTER TABLE todo
    ADD CONSTRAINT FK_TODO_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

-- changeset r.pereira:1730243249645-8
ALTER TABLE user_whatsapp
    ADD CONSTRAINT FK_USER_WHATSAPP_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

-- changeset r.pereira:1730243249645-12
DROP TABLE company CASCADE;

-- changeset r.pereira:1730243249645-13
DROP TABLE employee CASCADE;

-- changeset r.pereira:1730243249645-14
DROP SEQUENCE company_seq CASCADE;

-- changeset r.pereira:1730243249645-15
DROP SEQUENCE employee_seq CASCADE;
