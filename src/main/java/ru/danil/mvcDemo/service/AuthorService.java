package ru.danil.mvcDemo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.mvcDemo.repository.AuthorsRepository;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorsRepository authorsRepository;

    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }
}
