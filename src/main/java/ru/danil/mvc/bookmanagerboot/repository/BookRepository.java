package ru.danil.mvc.bookmanagerboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvc.bookmanagerboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
