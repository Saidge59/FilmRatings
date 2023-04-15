package com.example.Film_rating.service;

import com.example.Film_rating.dto.FilmDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService service = new FilmServiceImpl(filmRepository);

    @BeforeEach
    public void init() { MockitoAnnotations.openMocks(this); }

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
        FilmDTO filmDTO = new FilmDTO("testTitle", "testDirector", 0.0f, 0.0f);
        Film film = new Film(filmDTO.getTitle(), filmDTO.getDirector(), filmDTO.getBudget(), filmDTO.getRating());

        given(filmRepository.save(any(Film.class))).willReturn(film);
        Film film1 = service.saveFilm(filmDTO);

        assertEquals(film1.getTitle(), "testTitle");
        assertEquals(film1.getDirector(), "testDirector");
        assertEquals(film1.getBudget(), 0.0f);
        assertEquals(film1.getRating(), 0.0f);
    }

    @Test
    void getFilmById() {
        Film film = new Film("testTitle", "testDirector", 0.0f, 0.0f);
        film.setId(1);

        given(filmRepository.findById(1)).willReturn(Optional.of(film));
        Film film1 = service.getFilmById(1);

        assertEquals(film1.getId(), film.getId());
        assertEquals(film1.getTitle(), "testTitle");
        assertEquals(film1.getDirector(), "testDirector");
        assertEquals(film1.getBudget(), 0.0f);
        assertEquals(film1.getRating(), 0.0f);
    }

    @Test
    void updateFilm() {
        FilmDTO filmDTO = new FilmDTO("testTitle", "testDirector", 0.0f, 0.0f);
        Film film = new Film(filmDTO.getTitle(), filmDTO.getDirector(), filmDTO.getBudget(), filmDTO.getRating());
        float rating = 10.0f;

        given(filmRepository.save(any(Film.class))).willAnswer(f -> {
            Film film1 = f.getArgument(0);
            film1.setId(1);
            film1.setRating(rating);
            return film1;
        });
        Film film1 = service.saveFilm(filmDTO);

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