package ru.danil.mvcDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvcDemo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
