package ru.danil.mvcDemo.model;

import java.time.LocalDate;

public class Person {
    private int person_id;
    private String FIO;
    private LocalDate date_of_birth;

    public Person(int person_id, String FIO, LocalDate date_of_birth) {
        this.person_id = person_id;
        this.FIO = FIO;
        this.date_of_birth = date_of_birth;
    }

    public Person() {}

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
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
