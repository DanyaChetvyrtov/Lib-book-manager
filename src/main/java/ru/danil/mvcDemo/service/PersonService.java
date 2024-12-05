package ru.danil.mvcDemo.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.model.Book;
import ru.danil.mvcDemo.model.Person;
import ru.danil.mvcDemo.repository.PersonRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Book> findAllBooksByPersonId(int id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) return null;

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
        personRepository.delete(person);
    }

    @Transactional
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }
}
