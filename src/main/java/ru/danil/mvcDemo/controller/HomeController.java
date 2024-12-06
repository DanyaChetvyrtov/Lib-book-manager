package ru.danil.mvcDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// Контроллер для взаимодействия моделей book и person
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(){
        return "home_page";
    }
//    private final PersonBookDAO personBookDAO;
//
//    public AdminController(PersonBookDAO personBookDAO) {
//        this.personBookDAO = personBookDAO;
//    }
//
//    @PatchMapping("/give_book")
//    public String giveBook(
//            @RequestParam("book_id") int book_id,
//            @RequestParam("person_id") int person_id
//    ){
//
//        personBookDAO.assignBookToPerson(person_id, book_id);
//        return "redirect:/books";
//    }
//
//    @PatchMapping("/take_back")
//    public String takeBack(
//            @RequestParam("book_id") int book_id
//    ){
//
//        personBookDAO.takeBackFromPerson(book_id);
//        return "redirect:/books";
//    }
}
