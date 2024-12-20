package ru.danil.mvcDemo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.model.Author;
import ru.danil.mvcDemo.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String authors(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pagePerRequest", required = false, defaultValue = "5") int itemsPerRequest,
            Model model
    ){
        model.addAttribute("authors", authorService.findAll(page, itemsPerRequest));
        return "author/author_list";
    }

    @GetMapping("/{id}")
    public String author(@PathVariable("id") int id, Model model){
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("books", authorService.findBooksById(id));

        return "author/author_page";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("author", new Author());

        return "author/create_author";
    }

    @PostMapping
    public String createAuthor(
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return "author/create_author";
        }

        authorService.save(author);

        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("author", authorService.findById(id));

        return "author/edit_author";
    }

    @PatchMapping("/{id}")
    public String editBook(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return "author/edit_author";
        }
        authorService.update(id, author);

        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
