INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Avatar: The Way of Water', 'James Cameron', 460.0, 7.8);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Puss in Boots: The Last Wish', 'Joel Crawford, Januel Mercado', 90.0, 7.8);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('M3GAN', 'Gerard Johnstone', 12.0, 6.5);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Missing', 'Nicholas D. Johnson, Will Merrick', 7.0, 7.4);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('A Man Called Otto', 'Marc Forster', 50.0, 7.6);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Pathaan', 'Siddharth Anand', 30.6, 6.9);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Plane', 'Jean-François Richet', 25.0, 6.9);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Infinity Pool', 'Brandon Cronenberg', 12.7, 6.9);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Left Behind: Rise of the Antichrist', 'Kevin Sorbo', 16.0, 4.8);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('The Wandering Earth 2', 'Frant Gwo', 162.1, 8.0);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Fear', 'Deon Taylor', 1.3, 3.5);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('The Whale', 'Darren Aronofsky', 3.0, 8.0);
INSERT INTO FILMS_RATING (TITLE, DIRECTOR, BUDGET, RATING) VALUES ('Everything Everywhere All at Once', 'Dan Kwan, Daniel Scheinert', 14.3, 8.0);
--password: admin
INSERT INTO USERS (email, name, password) VALUES ('admin@gmail.com', 'admin', '$2a$10$f/hoT4dfy6JLn08nlFv72.Yzfc.TStVY8oQlmGctH9A1IopEuWcVq');
INSERT INTO ROLES (name) VALUES ('ADMIN');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
--password: denis
INSERT INTO USERS (email, name, password) VALUES ('denis@gmail.com', 'denis', '$2a$10$HotB34U8heDdWQJgb0d6MeEK.5C1/OHCK1pSgtdT7z3XyO3tOO4Xy');
INSERT INTO ROLES (name) VALUES ('USER');
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);