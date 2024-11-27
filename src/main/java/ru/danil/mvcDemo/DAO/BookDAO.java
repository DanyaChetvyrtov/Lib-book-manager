package ru.danil.mvcDemo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danil.mvcDemo.model.Book;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        return jdbcTemplate.query(
                "SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE book_id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findFirst().orElse(null);
    }

    public void addBook(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author, date_of_release) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getDate_of_release()
        );
    }
}
