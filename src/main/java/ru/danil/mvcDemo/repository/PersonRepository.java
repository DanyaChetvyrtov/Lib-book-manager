package ru.danil.mvcDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvcDemo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String firstName);

    Person findByEmail(String email);

    Person findByPhoneNumber(String phoneNumber);
}
