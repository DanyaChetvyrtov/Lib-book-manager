package ru.danil.mvcDemo.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.mvcDemo.DAO.BookDAO;
import ru.danil.mvcDemo.DAO.PersonDAO;
import ru.danil.mvcDemo.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "book/book_list";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model){
        Book book = bookDAO.getBookById(id);
        model.addAttribute("book", book);

        // Проверка была ли назначина за кем-то книга
        if(book.getPerson_id() == null)
            model.addAttribute("people", personDAO.getPeople());
        else
            model.addAttribute("person", personDAO.getPersonById(book.getPerson_id()));

        return "book/book_page";
    }

    @GetMapping("/new")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "book/create_book";
    }

    @PostMapping
    public String createNewBook(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return "book/create_book";

        bookDAO.addBook(book);
        return "redirect: /books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("cur_book_id", id);
        return "book/edit_book";
    }

    @PatchMapping("/{id}")
    public String updateBook(
            @PathVariable("id") int id,
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("cur_book_id", id);
            return "book/edit_book";
        }

        bookDAO.update(id, book);
        return "book/book_page";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect: /books";
    }
}
