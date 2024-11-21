package ru.danil.mvcDemo.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.danil.mvcDemo.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPerson_id(rs.getInt("person_id"));
        person.setFIO(rs.getString("fio"));
        person.setDate_of_birth(LocalDate.parse(rs.getString("date_of_birth")));

        System.out.println(person);
        return person;
    }
}
