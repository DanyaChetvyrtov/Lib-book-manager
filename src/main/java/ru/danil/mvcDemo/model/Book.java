package ru.danil.mvcDemo.model;

import java.time.LocalDate;

public class Book {
    private Integer book_id;
    private Integer person_id;
    private String title;
    private String author;
    private LocalDate date_of_release;

    public Book(Integer book_id, Integer person_id, String title, String author, LocalDate date_of_release) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.title = title;
        this.author = author;
        this.date_of_release = date_of_release;
    }

    public Book() {}

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate_of_release() {
        return date_of_release;
    }

    public void setDate_of_release(LocalDate date_of_release) {
        this.date_of_release = date_of_release;
    }
}
