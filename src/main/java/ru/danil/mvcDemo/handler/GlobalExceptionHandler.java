package ru.danil.mvcDemo.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.danil.mvcDemo.exception.AuthorNotFound;
import ru.danil.mvcDemo.exception.BookNotFound;
import ru.danil.mvcDemo.exception.PersonNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFound.class, PersonNotFound.class, AuthorNotFound.class})
    public String handleNotFoundException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/404";
    }
}
