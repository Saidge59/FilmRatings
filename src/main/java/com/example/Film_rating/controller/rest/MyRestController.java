package com.example.Film_rating.controller.rest;

import com.example.Film_rating.dto.FilmDTO;
import com.example.Film_rating.entity.Film;
import com.example.Film_rating.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/films")
public class MyRestController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> showAllFilms() {
        List<Film> filmList = filmService.getAllFilms();
        return filmList;
    }

    @GetMapping("/{id}")
    public Film getFilms(@PathVariable int id) {
        Film film = null;
        try {
            film = filmService.getFilmById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("There is no film with ID = " + id + " in Database");
        }

        return film;
    }

    @PostMapping
    public Film addFilm(@RequestBody FilmDTO filmDTO) {
        return filmService.saveFilm(filmDTO);
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable int id) {
        Film film = null;
        try {
            film = filmService.getFilmById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("There is no film with ID = " + id + " in Database");
        }
        filmService.deleteFilm(id);
        return "Films with ID = " + id + " was deleted!";
    }
}
