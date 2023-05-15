package com.example.films_ratings.repository;

import com.example.films_ratings.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Film findFilmByTitle(String title);
}
