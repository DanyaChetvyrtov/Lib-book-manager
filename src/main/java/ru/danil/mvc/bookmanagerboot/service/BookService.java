package ru.danil.mvc.bookmanagerboot.service;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvc.bookmanagerboot.exception.BookNotFound;
import ru.danil.mvc.bookmanagerboot.model.Author;
import ru.danil.mvc.bookmanagerboot.model.Book;
import ru.danil.mvc.bookmanagerboot.model.BookStatus;
import ru.danil.mvc.bookmanagerboot.model.Person;
import ru.danil.mvc.bookmanagerboot.model.enumirate.BookStatusEnum;
import ru.danil.mvc.bookmanagerboot.repository.BookRepository;
import ru.danil.mvc.bookmanagerboot.repository.PersonRepository;

import java.util.Date;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public Page<Book> findAll(int page, int itemsPerPage) {
        return bookRepository.findAll(PageRequest.of(page - 1, itemsPerPage, Sort.by("title")));
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFound("Book with ID = " + id + " not found")
        );
    }

    public Person findCurOwner(int id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFound("Book not found")
        );

        Hibernate.initialize(book.getCurBookOwner());
        return book.getCurBookOwner();
    }

    public Author findAuthor(int id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFound("Book not found")
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

        book.setCurBookOwner(person);
        book.getBookStatus().setTaking_date(new Date());
        book.getBookStatus().setCurrentStatus(BookStatusEnum.RESERVED);

        bookRepository.save(book);
    }

    @Transactional
    public void returnBook(int bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BookNotFound("Book not found")
        );

        Person curOwner = book.getCurBookOwner();

        if(curOwner == null) throw new RuntimeException("Book has no owner");

        book.getBookStatus().setCurrentStatus(BookStatusEnum.AVAILABLE);
        book.getBookStatus().setTaking_date(null);
        book.setCurBookOwner(null);

        curOwner.setReservedBook(null);

        bookRepository.save(book);
    }
}
