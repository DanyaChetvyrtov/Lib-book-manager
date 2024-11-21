package ru.danil.mvcDemo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danil.mvcDemo.mapper.PersonMapper;
import ru.danil.mvcDemo.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        System.out.println("!!!!!! Тут ещё всё окей");

        return jdbcTemplate.query(
                "SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class)
//                new PersonMapper()
        );
    }

    public Person getPersonById(int id){
        return jdbcTemplate.query(
                "SELECT person_id AS personId, FIO AS fio, date_of_birth AS dateOfBirth FROM person WHERE person_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findFirst().orElse(null);
    }
}
