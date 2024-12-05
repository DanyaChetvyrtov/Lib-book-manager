package ru.danil.mvcDemo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.model.Person;
import ru.danil.mvcDemo.repository.AuthorsRepository;
import ru.danil.mvcDemo.repository.BookRepository;
import ru.danil.mvcDemo.repository.PersonRepository;
import ru.danil.mvcDemo.service.BookService;
import ru.danil.mvcDemo.service.PersonService;


@Controller
@RequestMapping("/people")
public class PersonController {
    private final BookService bookService;
    private final PersonService personService;
    private final AuthorsRepository authorsRepository;

    public PersonController(BookService bookService, PersonService personService, AuthorsRepository authorsRepository) {
        this.bookService = bookService;
        this.personService = personService;
        this.authorsRepository = authorsRepository;
    }

    @GetMapping
    public String people(Model model){
        model.addAttribute("people", personService.findAll());
        return "person/person_list";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", personService.findAllBooksByPersonId(id));

        return "person/person_profile";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("person", new Person());
        return "person/create_person";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("person") Person person,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return "person/create_person";
        }

        personService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "person/edit_person";
    }

    @PatchMapping("/{id}")
    public String editPerson(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("person") Person person,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return "person/edit_person";
        }
        personService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personService.deleteById(id);
        return "redirect:/people";
    }
}
