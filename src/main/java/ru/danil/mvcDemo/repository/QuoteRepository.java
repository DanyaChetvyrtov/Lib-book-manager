package ru.danil.mvcDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danil.mvcDemo.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
