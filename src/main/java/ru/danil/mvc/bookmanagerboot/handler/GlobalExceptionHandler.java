package ru.danil.mvc.bookmanagerboot.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.danil.mvc.bookmanagerboot.exception.AuthorNotFound;
import ru.danil.mvc.bookmanagerboot.exception.BookNotFound;
import ru.danil.mvc.bookmanagerboot.exception.PersonNotFound;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFound.class, PersonNotFound.class, AuthorNotFound.class})
    public String handleNotFoundException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/500";
    }
}
