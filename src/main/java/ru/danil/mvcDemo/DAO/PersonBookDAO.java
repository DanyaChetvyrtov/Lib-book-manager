package ru.danil.mvcDemo.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danil.mvcDemo.model.Book;

import java.util.List;

@Component
public class PersonBookDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void assignBookToPerson(int person_id, int book_id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id = ? WHERE book_id = ?;",
                person_id, book_id
        );
    }

    public List<Book> getPersonBooks(int person_id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE person_id = ?",
                new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class)
        );
    }
}
