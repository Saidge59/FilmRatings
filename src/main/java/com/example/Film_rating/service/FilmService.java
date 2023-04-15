package com.example.Film_rating.service;

import com.example.Film_rating.dto.FilmDTO;
import com.example.Film_rating.entity.Film;
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
