package com.example.Film_rating.repository;

import com.example.Film_rating.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Film findFilmByTitle(String title);
}
