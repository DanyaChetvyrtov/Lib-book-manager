package ru.danil.mvc.bookmanagerboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvc.bookmanagerboot.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
