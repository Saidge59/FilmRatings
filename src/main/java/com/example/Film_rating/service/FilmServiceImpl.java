package com.example.Film_rating.service;

import com.example.Film_rating.dto.FilmDTO;
import com.example.Film_rating.entity.Film;
import com.example.Film_rating.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film saveFilm(FilmDTO filmDTO) {
        Film film = new Film(filmDTO.getTitle(), filmDTO.getDirector(), filmDTO.getBudget(), filmDTO.getRating());
        return filmRepository.save(film);
    }

    @Override
    public Film getFilmById(int id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public Film updateFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Page<Film> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return filmRepository.findAll(pageable);
    }

    @Override
    public boolean checkTitle(FilmDTO filmDTO) {
        Film film = filmRepository.findFilmByTitle(filmDTO.getTitle());
        return film != null;
    }
}
