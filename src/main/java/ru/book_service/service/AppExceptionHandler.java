package ru.book_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.book_service.exception.IncorrectInputException;

@Slf4j
@Controller
public class AppExceptionHandler {

    @ExceptionHandler(IncorrectInputException.class)
    public String handleIncorrectInputException(IncorrectInputException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        log.error("Error {}", ex.getMessage(), ex);
        return "Internal server error";
    }

}
