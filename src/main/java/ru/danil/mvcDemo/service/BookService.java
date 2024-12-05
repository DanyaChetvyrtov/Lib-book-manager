package ru.danil.mvcDemo.service;

import org.springframework.stereotype.Service;
import ru.danil.mvcDemo.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
