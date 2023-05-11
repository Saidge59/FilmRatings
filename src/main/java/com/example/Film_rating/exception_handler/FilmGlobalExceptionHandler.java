package com.example.Film_rating.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class FilmGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<FilmIncorrectData> handleException1 (NoSuchElementException exception) {
        FilmIncorrectData data = new FilmIncorrectData();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<FilmIncorrectData> handleException2 (Exception exception) {
//        FilmIncorrectData data = new FilmIncorrectData();
//        data.setMessage(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }

}
