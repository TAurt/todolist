DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(128)  NOT NULL,
    birthday DATE          NOT NULL,
    email    VARCHAR(128)  NOT NULL UNIQUE,
    password VARCHAR(128)  NOT NULL,
    role     VARCHAR(128)  NOT NULL,
    image    VARCHAR(1024) NOT NULL
);

CREATE TABLE task
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        INTEGER      NOT NULL,
    title          VARCHAR(128) NOT NULL,
    created_date   DATE         NOT NULL,
    scheduled_date DATE         NOT NULL,
    completed_date DATE,
    status         VARCHAR(128) NOT NULL,
    description    varchar(256),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


