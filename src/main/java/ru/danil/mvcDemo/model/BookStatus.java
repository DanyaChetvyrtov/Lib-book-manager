package ru.danil.mvcDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.danil.mvcDemo.model.enumirate.BookStatusEnum;

import java.util.Date;

@Entity
@Table(name = "book_status")
public class BookStatus {
    @Id
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Book book;

    @Column(name = "current_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private BookStatusEnum currentStatus;

    @Column(name = "taking_date")
    private Date taking_date;


    public BookStatus() {
    }

    public BookStatus(Book book, BookStatusEnum currentStatus, Date taking_date) {
        this.book = book;
        this.currentStatus = currentStatus;
        this.taking_date = taking_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public @NotNull BookStatusEnum getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(@NotNull BookStatusEnum currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getTaking_date() {
        return taking_date;
    }

    public void setTaking_date(Date taking_date) {
        this.taking_date = taking_date;
    }

    @Override
    public String toString() {
        return "BookStatus{" +
                "currentStatus=" + currentStatus +
                '}';
    }
}
