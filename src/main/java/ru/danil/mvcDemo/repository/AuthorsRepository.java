package ru.danil.mvcDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvcDemo.model.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
