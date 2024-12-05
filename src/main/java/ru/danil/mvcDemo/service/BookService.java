package ru.danil.mvcDemo.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.model.Author;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.model.Person;
import ru.danil.mvcDemo.repository.BookRepository;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Person findCurOwner(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) return null;

        Hibernate.initialize(book.getCurBookOwner());
        return book.getCurBookOwner();
    }

    public Author findAuthor(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) return null;

        Hibernate.initialize(book.getBookAuthor());
        return book.getBookAuthor();
    }

    @Transactional
    public void save(Book book) {
        book.setCreatedAt(new Date());
        Author tempAuth = new Author();
        tempAuth.setId(book.getAuthor_id());
        book.setBookAuthor(tempAuth);

        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        Author tempAuth = new Author();
        tempAuth.setId(book.getAuthor_id());
        book.setBookAuthor(tempAuth);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
