--liquibase formatted sql
--changeset mustlive:1
insert into users (id, username, password) values (1, 'admin', '123')
ON CONFLICT (username) DO NOTHING;
-- rollback TRUNCATE Table users;
--changeset mustlive:2
insert into roles (id, rolename) values
                                     (1, 'ADMIN'),
                                     (2, 'USER')
ON CONFLICT (id) DO NOTHING;
--rollback TRUNCATE Table roles;
--changeset mustlive:3
insert into users_roles (user_id, role_id) values (1, 1)
ON CONFLICT DO NOTHING;
-- rollback TRUNCATE Table users_roles;