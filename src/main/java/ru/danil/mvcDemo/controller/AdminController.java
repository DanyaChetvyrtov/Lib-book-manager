package ru.danil.mvcDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.danil.mvcDemo.DAO.PersonBookDAO;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.model.Person;


// Контроллер для взаимодействия моделей book и person
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonBookDAO personBookDAO;

    public AdminController(PersonBookDAO personBookDAO) {
        this.personBookDAO = personBookDAO;
    }

    @PatchMapping("/give_book")
    public String giveBook(
            @RequestParam("book_id") int book_id,
            @RequestParam("person_id") int person_id
    ){

        personBookDAO.assignBookToPerson(person_id, book_id);
        return "redirect:/books";
    }

    @PatchMapping("/take_back")
    public String takeBack(
            @RequestParam("book_id") int book_id
    ){

        personBookDAO.takeBackFromPerson(book_id);
        return "redirect:/books";
    }
}
