package ru.danil.mvcDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    @Size(min = 10, max = 60, message = "Ваше фио должно содержать от 10 до 60 символов")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+",
            message = "Фио должно соответствовать: Фамилия Имя Отчество\n(обратите внимание на пробелы)")
    private String fullName;

    @Column(name = "age")
    @Max(value = 99, message = "Возраст не может быть больше 99")
    @Min(value = 11, message = "Возраст не может быть меньше 11")
    private Integer age;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "введите email")
    @Size(max = 100, message = "Email не может содержать более 100 симовлов")
    private String email;

    @Column(name = "phone_number")
    @NotEmpty(message = "Номер не должен быть пуст")
    @Pattern(
            regexp = "\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}",
            message = "Формат номера должен быть +7(000)000-00-00"
    )
    private String phoneNumber;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "curBookOwner")
    private List<Book> reservedBooks;

    public Person() {}

    public Person(Integer id, String fullName, Integer age, String email, String phoneNumber, Date createdAt, List<Book> reservedBooks) {
        this.id = id;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String full_name) {
        this.fullName = full_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
