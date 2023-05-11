CREATE TABLE films_rating (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    budget FLOAT NOT NULL,
    rating FLOAT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO films_rating (title, director, budget, rating)
VALUES
    ('Avatar: The Way of Water', 'James Cameron', 460.0, 7.8),
    ('Puss in Boots: The Last Wish', 'Joel Crawford, Januel Mercado', 90.0, 7.8),
    ('M3GAN', 'Gerard Johnstone', 12.0, 6.5),
    ('Missing', 'Nicholas D. Johnson, Will Merrick', 7.0, 7.4),
    ('A Man Called Otto', 'Marc Forster', 50.0, 7.6),
    ('Pathaan', 'Siddharth Anand', 30.6, 6.9),
    ('Plane', 'Jean-François Richet', 25.0, 6.9),
    ('Infinity Pool', 'Brandon Cronenberg', 12.7, 6.9),
    ('Left Behind: Rise of the Antichrist', 'Kevin Sorbo', 16.0, 4.8),
    ('The Wandering Earth 2', 'Frant Gwo', 162.1, 8.0),
    ('Fear', 'Deon Taylor', 1.3, 3.5),
    ('The Whale', 'Darren Aronofsky', 3.0, 8.0),
    ('Everything Everywhere All at Once', 'Dan Kwan, Daniel Scheinert', 14.3, 8.0);