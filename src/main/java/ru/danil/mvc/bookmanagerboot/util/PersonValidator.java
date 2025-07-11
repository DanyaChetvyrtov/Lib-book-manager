package ru.danil.mvc.bookmanagerboot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danil.mvc.bookmanagerboot.model.Person;
import ru.danil.mvc.bookmanagerboot.repository.PersonRepository;


@Component
public class PersonValidator implements Validator {
    private final PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personRepository.findByFullName(person.getFullName()) != null)
            errors.rejectValue("fullName", "", "Читатель с таким ФИО уже существует");

        if(personRepository.findByEmail(person.getEmail()) != null)
            errors.rejectValue("email", "", "Читатель с такой почтой уже существует");

        if(personRepository.findByPhoneNumber(person.getPhoneNumber()) != null)
            errors.rejectValue("phoneNumber", "", "Читатель с таким номером уже существует");
    }
}
