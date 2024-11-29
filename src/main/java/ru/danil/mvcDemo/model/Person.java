package ru.danil.mvcDemo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Person {
    private Integer person_id;

    @NotEmpty
    @Size(min = 10, max = 60, message = "Ваше фио должно содержать от 10 до 60 символов")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+",
            message = "Фио должно соответствовать: Фамилия Имя Отчество\n(обратите внимание на пробелы)")
    private String FIO;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date_of_birth;

    public Person(Integer person_id, String FIO, LocalDate date_of_birth) {
        this.person_id = person_id;
        this.FIO = FIO;
        this.date_of_birth = date_of_birth;
    }

    public Person() {}

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", FIO='" + FIO + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
