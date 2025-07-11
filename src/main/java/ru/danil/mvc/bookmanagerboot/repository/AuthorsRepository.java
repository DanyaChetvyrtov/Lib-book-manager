package ru.danil.mvc.bookmanagerboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvc.bookmanagerboot.model.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Author findByFullName(String fullName);
}
