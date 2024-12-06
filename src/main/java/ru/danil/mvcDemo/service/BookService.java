package ru.danil.mvcDemo.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.model.Author;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.model.BookStatus;
import ru.danil.mvcDemo.model.Person;
import ru.danil.mvcDemo.model.enumirate.BookStatusEnum;
import ru.danil.mvcDemo.repository.BookRepository;
import ru.danil.mvcDemo.repository.PersonRepository;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
    }

    public Person findCurOwner(int id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );

        Hibernate.initialize(book.getCurBookOwner());
        return book.getCurBookOwner();
    }

    public Author findAuthor(int id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );

        Hibernate.initialize(book.getBookAuthor());
        return book.getBookAuthor();
    }

    @Transactional
    public void save(Book book) {
        Author tempAuth = new Author();
        BookStatus bookStatus = new BookStatus();

        tempAuth.setId(book.getAuthor_id());
        bookStatus.setBook(book);
        bookStatus.setCurrentStatus(BookStatusEnum.AVAILABLE);

        book.setCreatedAt(new Date());
        book.setBookAuthor(tempAuth);
        book.setBookStatus(bookStatus);

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

    @Transactional
    public void assignBook(int bookId, int personId){
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new RuntimeException("Person not found")
        );

        book.getBookStatus().setCurrentStatus(BookStatusEnum.RESERVED);
        book.getBookStatus().setTaking_date(new Date());
        book.setCurBookOwner(person);

        bookRepository.save(book);
    }

    @Transactional
    public void returnBook(int bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found")
        );

        Person curOwner = book.getCurBookOwner();

        if(curOwner == null) throw new RuntimeException("Book has not an owner");

        book.getBookStatus().setCurrentStatus(BookStatusEnum.AVAILABLE);
        book.getBookStatus().setTaking_date(null);

        book.setCurBookOwner(null);
        curOwner.setReservedBook(null);

        bookRepository.save(book);
    }
}
