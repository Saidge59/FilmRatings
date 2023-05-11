CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO users (email, name, password) VALUES ('admin@gmail.com', 'admin', '$2a$10$f/hoT4dfy6JLn08nlFv72.Yzfc.TStVY8oQlmGctH9A1IopEuWcVq');
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO users (email, name, password) VALUES ('denis@gmail.com', 'denis', '$2a$10$HotB34U8heDdWQJgb0d6MeEK.5C1/OHCK1pSgtdT7z3XyO3tOO4Xy');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);