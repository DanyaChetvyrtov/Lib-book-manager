package ru.danil.mvcDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    @NotEmpty
    @Size(min = 10, max = 100, message = "Ваше фио должно содержать от 10 до 60 символов")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+",
            message = "Фио должно соответствовать: Фамилия Имя Отчество\n(обратите внимание на пробелы)")
    private String full_name;

    @Column(name = "age")
    @Max(value = 99, message = "Возраст не может быть больше 99")
    @Min(value = 11, message = "Возраст не может быть меньше 11")
    private Integer age;

    @Column(name = "email")
    @Email
    @NotEmpty
    @Size(max = 100, message = "Email не может содержать более 100 симовлов")
    private String email;

    @Column(name = "phone_number")
    @NotEmpty
    @Size(max = 20, message = "Email не может содержать более 20 симовлов")
    private String phoneNumber;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "curBookOwner")
    private List<Book> reservedBooks;


    public Person() {
    }

    public Person(Integer id, String full_name, Integer age, String email, String phoneNumber, Date createdAt, List<Book> reservedBooks) {
        this.id = id;
        this.full_name = full_name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.reservedBooks = reservedBooks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty @Size(min = 10, max = 100, message = "Ваше фио должно содержать от 10 до 60 символов") @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+",
            message = "Фио должно соответствовать: Фамилия Имя Отчество\n(обратите внимание на пробелы)") String getFull_name() {
        return full_name;
    }

    public void setFull_name(@NotEmpty @Size(min = 10, max = 100, message = "Ваше фио должно содержать от 10 до 60 символов") @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+",
            message = "Фио должно соответствовать: Фамилия Имя Отчество\n(обратите внимание на пробелы)") String full_name) {
        this.full_name = full_name;
    }

    @Max(value = 99, message = "Возраст не может быть больше 99")
    @Min(value = 11, message = "Возраст не может быть меньше 11")
    public Integer getAge() {
        return age;
    }

    public void setAge(@Max(value = 99, message = "Возраст не может быть больше 99") @Min(value = 11, message = "Возраст не может быть меньше 11") Integer age) {
        this.age = age;
    }

    public @Email @NotEmpty @Size(max = 100, message = "Email не может содержать более 100 симовлов") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty @Size(max = 100, message = "Email не может содержать более 100 симовлов") String email) {
        this.email = email;
    }

    public @NotEmpty @Size(max = 20, message = "Email не может содержать более 20 симовлов") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty @Size(max = 20, message = "Email не может содержать более 20 симовлов") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Book> getReservedBook() {
        return reservedBooks;
    }

    public void setReservedBook(List<Book> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
