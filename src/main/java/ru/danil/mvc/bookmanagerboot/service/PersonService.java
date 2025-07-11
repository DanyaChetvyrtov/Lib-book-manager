package ru.danil.mvc.bookmanagerboot.service;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvc.bookmanagerboot.exception.PersonNotFound;
import ru.danil.mvc.bookmanagerboot.model.Book;
import ru.danil.mvc.bookmanagerboot.model.Person;
import ru.danil.mvc.bookmanagerboot.model.enumirate.BookStatusEnum;
import ru.danil.mvc.bookmanagerboot.repository.PersonRepository;

import java.util.Date;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page<Person> findAll(int page, int itemsPerPage) {
        return personRepository.findAll(PageRequest.of(page - 1, itemsPerPage, Sort.by("fullName")));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow(
                () -> new PersonNotFound("Person not found")
        );
    }

    public List<Book> findAllBooksByPersonId(int id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new PersonNotFound("Person not found")
        );

        Hibernate.initialize(person.getReservedBook());
        return person.getReservedBook();
    }

    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Person person) {

        person.getReservedBook().forEach(
                book -> {
                    book.getBookStatus().setCurrentStatus(BookStatusEnum.AVAILABLE);
                    book.getBookStatus().setTaking_date(null);
                    book.setCurBookOwner(null);
                }
        );

        person.setReservedBook(null);

        personRepository.delete(person);
    }

    @Transactional
    public void deleteById(int id) {
        Person personToDelete = personRepository.findById(id).orElseThrow(
                () -> new PersonNotFound("Person not found")
        );

        personToDelete.getReservedBook().forEach(
                book -> {
                    book.getBookStatus().setCurrentStatus(BookStatusEnum.AVAILABLE);
                    book.getBookStatus().setTaking_date(null);
                    book.setCurBookOwner(null);
                }
        );

        personToDelete.setReservedBook(null);

        personRepository.delete(personToDelete);
    }
}
