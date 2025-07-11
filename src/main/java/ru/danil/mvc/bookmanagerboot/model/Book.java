package ru.danil.mvc.bookmanagerboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Название может содержать от 2х до 100 символов")
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date releaseDate;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author bookAuthor;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person curBookOwner;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private BookStatus bookStatus;

    @Transient
    private Integer author_id;

    public Book() {}

    public Book(Integer id, String title, String description, Date releaseDate, Date createdAt, Author bookAuthor, Person curBookOwner, BookStatus bookStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.bookAuthor = bookAuthor;
        this.curBookOwner = curBookOwner;
        this.bookStatus = bookStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Author getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(Author bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Person getCurBookOwner() {
        return curBookOwner;
    }

    public void setCurBookOwner(Person curBookOwner) {
        this.curBookOwner = curBookOwner;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }


    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
