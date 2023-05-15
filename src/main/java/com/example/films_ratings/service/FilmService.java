package com.example.films_ratings.service;

import com.example.films_ratings.dto.FilmDTO;
import com.example.films_ratings.entity.Film;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();

    Film saveFilm(FilmDTO filmDTO);

    Film getFilmById(int id);

    Film updateFilm(Film film);

    void deleteFilm(int id);

    Page<Film> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);

    boolean checkTitle(FilmDTO filmDTO);
}
