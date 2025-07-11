package ru.danil.mvc.bookmanagerboot.util;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danil.mvc.bookmanagerboot.model.Author;
import ru.danil.mvc.bookmanagerboot.repository.AuthorsRepository;


@Controller
public class AuthorValidator implements Validator {
    private final AuthorsRepository authorsRepository;

    public AuthorValidator(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Author.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;

        if(authorsRepository.findByFullName(author.getFullName()) != null)
            errors.rejectValue("fullName", "", "Такой автор уже существует");
    }
}
