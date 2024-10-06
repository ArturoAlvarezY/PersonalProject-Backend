-- Inserts para los roles
INSERT INTO roles (role_id, name) VALUES 
(default, 'ROLE_ADMIN'),
(default, 'ROLE_USER');

-- Inserts para los usuarios
INSERT INTO users (id, username, password) VALUES 
(default, 'adminLucas@gmail.com', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa'),
(default, 'Bumby@gmail.com', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');

-- Asignación de roles a los usuarios
INSERT INTO user_roles (role_id, user_id) VALUES 
(1, 1),
(2, 2);

-- Creación de perfiles para los usuarios
INSERT INTO profiles (id, user_id) VALUES
(default, 1),
(default, 2);