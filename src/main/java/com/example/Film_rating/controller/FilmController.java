package com.example.Film_rating.controller;

import com.example.Film_rating.dto.FilmDTO;
import com.example.Film_rating.entity.Film;
import com.example.Film_rating.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class FilmController {
    private static final int MAX_NUM_PAGES = 8;
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public String listFilms(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/add")
    public String createFilm(Model model) {
        FilmDTO filmDTO = new FilmDTO();
        model.addAttribute("film", filmDTO);
        return "add-film";
    }

    @PostMapping("/film")
    public String saveFilm(@Valid @ModelAttribute("film") FilmDTO filmDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "add-film";
        }

        if (filmService.checkTitle(filmDTO)) {
            redirectAttributes.addFlashAttribute("errorTitle", filmDTO.getTitle());
            return "redirect:/add";
        }

        filmService.saveFilm(filmDTO);

        redirectAttributes.addFlashAttribute("success", "");
        return "redirect:/add";
    }

    @GetMapping("/edit/{id}")
    public String editFilm(@PathVariable Integer id, Model model) {
        Film film = null;
        try {
            film = filmService.getFilmById(id);
        } catch (NoSuchElementException e) {
            return "errors/404";
        }

        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setTitle(film.getTitle());
        filmDTO.setDirector(film.getDirector());
        filmDTO.setBudget(film.getBudget());
        filmDTO.setRating(film.getRating());

        model.addAttribute("film", filmDTO);
        model.addAttribute("id", film.getId());
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFilm(@PathVariable int id, @Valid @ModelAttribute("film") FilmDTO filmDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "edit";
        }

        Film film = null;
        try {
            film = filmService.getFilmById(id);
        } catch (NoSuchElementException e) {
            return "errors/404";
        }

        if (!film.getTitle().equals(filmDTO.getTitle())) {
            if (filmService.checkTitle(filmDTO)) {
                redirectAttributes.addFlashAttribute("errorTitle", filmDTO.getTitle());
                return "redirect:/edit/{id}";
            }
        }

        film.setId(id);
        film.setTitle(filmDTO.getTitle());
        film.setDirector(filmDTO.getDirector());
        film.setBudget(filmDTO.getBudget());
        film.setRating(filmDTO.getRating());

        filmService.updateFilm(film);

        redirectAttributes.addFlashAttribute("success", "");
        return "redirect:/edit/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable int id) {
        filmService.deleteFilm(id);
        return "redirect:/films";
    }

    @GetMapping("/films/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = MAX_NUM_PAGES;

        Page<Film> page;
        try {
            page = filmService.findPaginated(pageNo, pageSize, sortField, sortDir);
        } catch (PropertyReferenceException e) {
            return "errors/500";
        }

        List<Film> filmLists = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reversSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("films", filmLists);
        return "films";
    }
}
