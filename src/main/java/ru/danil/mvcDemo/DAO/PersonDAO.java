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
        return jdbcTemplate.query(
                "SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class)
//                new PersonMapper()
        );
    }

    public Person getPersonById(int id){
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE person_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findFirst().orElse(null);
    }

    public void addPerson(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person (fio, date_of_birth) VALUES (?, ?)",
                person.getFIO(), person.getDate_of_birth()
        );
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update(
                "UPDATE person SET fio = ?, date_of_birth = ? WHERE person_id = ?",
                person.getFIO(), person.getDate_of_birth(), id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(
              "DELETE FROM person WHERE person_id = ?",
              id
        );
    }
}
