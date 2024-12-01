package ru.danil.mvcDemo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.DAO.PersonBookDAO;
import ru.danil.mvcDemo.DAO.PersonDAO;
import ru.danil.mvcDemo.model.Person;


@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonBookDAO personBookDAO;

    public PersonController(PersonDAO personDAO, PersonBookDAO personBookDAO) {
        this.personDAO = personDAO;
        this.personBookDAO = personBookDAO;
    }

    @GetMapping
    public String getPeople(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "person/person_list";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("books", personBookDAO.getPersonBooks(id));
        return "person/person_profile";
    }

    @GetMapping("/new")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        return "person/create_person";
    }

    @PostMapping
    public String createNewPerson(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return "person/create_person";

        personDAO.addPerson(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("cur_person_id", id);

        return "person/edit_person";
    }

    @PatchMapping("/{id}")
    public String editPerson(
            @PathVariable("id") int id,
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult,
            Model model
    ){
        /**
            Смею предположить, что это достаточно костыльный способ решения
            однако это работает, и, пока что, я не знаю, как сделать лучше
            :\
        */
        if (bindingResult.hasErrors()){
            model.addAttribute("cur_person_id", id);
            return "person/edit_person";
        }

        personDAO.updatePerson(id, person);
        return "person/person_profile";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
