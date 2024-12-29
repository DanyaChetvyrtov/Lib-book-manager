package ru.danil.mvcDemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    private Integer id;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "author")
    @NotNull
    private String author;

    public Quote() {
    }

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
