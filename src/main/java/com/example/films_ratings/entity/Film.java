package com.example.films_ratings.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "films_rating")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "director")
    private String director;
    @Column(name = "budget")
    private float budget;
    @Column(name = "rating")
    private float rating;

    public Film(String title, String director, float budget, float rating) {
        this.title = title;
        this.director = director;
        this.budget = budget;
        this.rating = rating;
    }
}
