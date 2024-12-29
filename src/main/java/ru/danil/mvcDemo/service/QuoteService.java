package ru.danil.mvcDemo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.model.Quote;
import ru.danil.mvcDemo.repository.QuoteRepository;

import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        int rnd = new Random().nextInt(quotes.size());

        return quotes.get(rnd);
    }
}
