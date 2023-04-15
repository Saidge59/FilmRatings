package com.example.Film_rating.repository;

import com.example.Film_rating.entity.Film;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Test
    @Rollback(value = false)
    @Order(10)
    public void saveFilm() {
        Film addFilm = new Film("testTitle", "testDirector", 0.0f, 0.0f);
        Film filmFromDB = filmRepository.save(addFilm);
        System.out.println(filmFromDB);
        assertTrue(filmFromDB.getId() > 0);
    }

    @Test
    @Order(20)
    public void findAllFilmTest() {
        List<Film> films = filmRepository.findAll();
        assertTrue(films.size() > 0);
    }

    @Test
    @Order(30)
    public void getFilmById() {
        Film filmByTitle  = filmRepository.findFilmByTitle("testTitle");
        Film filmById = filmRepository.findById(filmByTitle.getId()).get();

        assertEquals(filmByTitle.getId(), filmById.getId());
    }

    @Test
    @Order(40)
    public void updateFilm() {
        float rating = 10.0f;

        Film filmByTitle = filmRepository.findFilmByTitle("testTitle");
        filmByTitle.setRating(rating);

        Film film = filmRepository.save(filmByTitle);
        assertNotEquals(film.getRating(), 0.0f);
    }

    @Test
    @Rollback(value = false)
    @Order(50)
    public void deleteFilm() {
        Film filmByTitle = filmRepository.findFilmByTitle("testTitle");

        filmRepository.deleteById(filmByTitle.getId());

        assertNull(filmRepository.findFilmByTitle("testTitle"));
    }
}