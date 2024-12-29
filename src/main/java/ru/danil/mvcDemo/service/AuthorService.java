package ru.danil.mvcDemo.service;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.exception.AuthorNotFound;
import ru.danil.mvcDemo.model.Author;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.repository.AuthorsRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorsRepository authorsRepository;

    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Page<Author> findAll(int page, int itemsPerPage) {
        return authorsRepository.findAll(PageRequest.of(page - 1, itemsPerPage, Sort.by("fullName")));
    }

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Author findById(int id) {
        return authorsRepository.findById(id).orElseThrow(
                () -> new AuthorNotFound("Author not found")
        );
    }

    public List<Book> findBooksById(int id) {
        Author author = authorsRepository.findById(id).orElseThrow(
                () -> new AuthorNotFound("Author not found")
        );

        Hibernate.initialize(author.getBooks());
        return author.getBooks();
    }

    @Transactional
    public void save(Author author) {
        author.setCreatedAt(new Date());
        authorsRepository.save(author);
    }

    @Transactional
    public void update(int id, Author author) {
        author.setId(id);
        authorsRepository.save(author);
    }

    @Transactional
    public void delete(Author author) {
        authorsRepository.delete(author);
    }

    @Transactional
    public void deleteById(int id) {
        authorsRepository.deleteById(id);
    }
}
