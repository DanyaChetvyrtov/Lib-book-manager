package ru.danil.mvcDemo.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.service.AuthorService;
import ru.danil.mvcDemo.service.BookService;
import ru.danil.mvcDemo.service.PersonService;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PersonService personService;

    public BookController(BookService bookService, AuthorService authorService, PersonService personService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.personService = personService;
    }

    @GetMapping
    public String books(Model model){
        model.addAttribute("books", bookService.findAll());
        return "book/book_list";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findById(id));

        if(bookService.findCurOwner(id) == null)
            model.addAttribute("people", personService.findAll());
        else
            model.addAttribute("owner", bookService.findCurOwner(id));

        return "book/book_page";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());

        return "book/create_book";
    }

    @PostMapping
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return "book/create_book";
        }

        bookService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());

        return "book/edit_book";
    }

    @PatchMapping("/{id}")
    public String editBook(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return "book/edit_book";
        }
        bookService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign_book")
    public String assignBook(
            @PathVariable("id") int id,
            @RequestParam("person_id") int person_id
    ){

        bookService.assignBook(id, person_id);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/return_book")
    public String returnBook(
            @PathVariable("id") int id
    ){
        bookService.returnBook(id);

        return "redirect:/books/" + id;
    }
}
