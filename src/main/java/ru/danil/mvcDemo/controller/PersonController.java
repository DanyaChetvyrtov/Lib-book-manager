package ru.danil.mvcDemo.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.model.Person;
import ru.danil.mvcDemo.repository.AuthorsRepository;
import ru.danil.mvcDemo.service.BookService;
import ru.danil.mvcDemo.service.PersonService;
import ru.danil.mvcDemo.util.PersonValidator;


@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;

    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String people(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model
    ){
        Page<Person> people = personService.findAll(page, size);
        model.addAttribute("people", people.getContent());
        model.addAttribute("currentPage", people.getNumber() + 1);
        model.addAttribute("totalPages", people.getTotalPages());
        model.addAttribute("pageSize", size);

        return "person/person_list";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", personService.findAllBooksByPersonId(id));

        return "person/person_page";
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
        personValidator.validate(person, bindingResult);

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
