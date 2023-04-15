package com.example.Film_rating.service;

import com.example.Film_rating.entity.Film;
import com.example.Film_rating.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService service = new FilmServiceImpl(filmRepository);

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllFilmsTest() {
        List<Film> filmList = new ArrayList<Film>();

        filmList.add(new Film("testTitle1", "testDirector1", 0.0f, 0.0f));
        filmList.add(new Film("testTitle2", "testDirector2", 0.0f, 0.0f));
        filmList.add(new Film("testTitle3", "testDirector3", 0.0f, 0.0f));

        given(filmRepository.findAll()).willReturn(filmList);
        assertEquals(service.getAllFilms().size(), filmList.size());
    }

    @Test
    void saveFilm() {
        Film film = new Film("testTitle", "testDirector", 0.0f, 0.0f);

        given(filmRepository.save(film)).willReturn(film);
        Film film1 = service.saveFilm(film);

        assertEquals(film1.getTitle(), "testTitle");
        assertEquals(film1.getDirector(), "testDirector");
        assertEquals(film1.getBudget(), 0.0f);
        assertEquals(film1.getRating(), 0.0f);
    }

    @Test
    void getFilmById() {
        Film film = new Film("testTitle", "testDirector", 0.0f, 0.0f);
        film.setId(1);

        Optional<Film> optionalFilm = Optional.ofNullable(film);

        given(filmRepository.findById(1)).willReturn(optionalFilm);
        Film film1 = service.getFilmById(1);

        assertEquals(film1.getId(), film.getId());
        assertEquals(film1.getTitle(), "testTitle");
        assertEquals(film1.getDirector(), "testDirector");
        assertEquals(film1.getBudget(), 0.0f);
        assertEquals(film1.getRating(), 0.0f);
    }

    @Test
    void updateFilm() {
        Film film = new Film("testTitle", "testDirector", 0.0f, 0.0f);
        float rating = 10.0f;

        given(filmRepository.save(film)).willAnswer(f -> {
            Film film1 = f.getArgument(0);
            film1.setId(1);
            film1.setRating(rating);
            return film1;
        });
        Film film1 = service.saveFilm(film);

        assertNotEquals(film1.getId(), 0);
        assertEquals(film1.getTitle(), "testTitle");
        assertEquals(film1.getDirector(), "testDirector");
        assertEquals(film1.getBudget(), 0.0f);
        assertEquals(film1.getRating(), rating);
    }

    @Test
    void deleteFilm() {
        filmRepository.deleteById(1);
        Mockito.verify(filmRepository, Mockito.times(1)).deleteById(1);
    }
}