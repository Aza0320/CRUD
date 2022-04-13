package aza.test.spring.testpeople.testpeople.web.dao;

import aza.test.spring.testpeople.testpeople.web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person order by id", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (passport, name, surname, address, sex, dob, doe, doi ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", person.getPassport().toUpperCase(Locale.ROOT), person.getName(), person.getSurname(), person.getAddress(), person.getSex(), person.getDob(), person.getDoe(), person.getDoi());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE Person SET passport=?, name=?, surname=?, address=?, sex=?, dob=?, doe=?, doi=? WHERE id=?", person.getPassport().toLowerCase(Locale.ROOT), person.getName(), person.getSurname(), person.getAddress(), person.getSex(), person.getDob(), person.getDoe(), person.getDoi(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)).stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public Person getPersonByPassport(String passport) {
        return jdbcTemplate.query("SELECT * FROM Person where passport like ?", new BeanPropertyRowMapper<>(Person.class), passport.toUpperCase(Locale.ROOT)).stream().findAny().orElse(null);
    }
}
