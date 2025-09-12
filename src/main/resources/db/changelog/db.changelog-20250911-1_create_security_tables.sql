--liquibase formatted sql

--changeset mustlive:1
CREATE TABLE IF NOT EXISTS roles
(
    id BIGINT PRIMARY KEY,
    rolename VARCHAR(64) NOT NULL UNIQUE
);
--rollback DROP TABLE roles

--changeset mustlive:2
CREATE TABLE IF NOT EXISTS users
(
    id BIGINT PRIMARY KEY,
    username VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );
--rollback DROP TABLE users

--changeset mustlive:3
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_role
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
    );
--rollback DROP TABLE users_roles