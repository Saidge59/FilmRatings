package com.example.films_ratings.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "errors/400";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/500";
            }
        }
        return "error";
    }

    @RequestMapping("/access-denied")
    public String getAccessDenied() {
        return "errors/403";
    }
}