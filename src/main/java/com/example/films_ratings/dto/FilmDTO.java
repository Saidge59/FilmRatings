package com.example.films_ratings.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilmDTO {

    @Size(min=2, message = " Title must be min 2 symbols")
    private String title;

    @Size(min=2, message = " Director must be min 2 symbols")
    private String director;

    private float budget;
    private float rating;
}
