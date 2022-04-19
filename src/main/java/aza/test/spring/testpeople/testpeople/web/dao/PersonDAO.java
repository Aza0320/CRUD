package aza.test.spring.testpeople.testpeople.web.dao;

import aza.test.spring.testpeople.testpeople.web.models.*;
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

    public List<Test> getTest(int offset, int next, String sex, String country, String region, String dob) {
        return jdbcTemplate.query("SELECT * FROM Test Where sex = ? and country = ? and region = ? and dob = ?" +
                        " order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ",
                new BeanPropertyRowMapper<>(Test.class), sex, country, region, dob, offset, next);
    }

    public List<Test> getTest(int offset, int next, String sex, String country, String region) {
        return jdbcTemplate.query("SELECT * FROM Test Where sex = ? and country = ? and region = ? " +
                        "order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ",
                new BeanPropertyRowMapper<>(Test.class), sex, country, region, offset, next);
    }

    public List<Test> getTest(int offset, int next, String sex, String country) {
        return jdbcTemplate.query("SELECT * FROM Test Where sex = ? and country = ? " +
                        "order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ",
                new BeanPropertyRowMapper<>(Test.class), sex, country, offset, next);
    }

    public List<Test> getTest(int offset, int next, String sex) {
        return jdbcTemplate.query("SELECT * FROM Test Where sex = ? " +
                        "order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ",
                new BeanPropertyRowMapper<>(Test.class), sex, offset, next);
    }

    public List<Test> getTest(int offset, int next) {
        return jdbcTemplate.query("SELECT * FROM Test order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ",
                new BeanPropertyRowMapper<>(Test.class), offset, next);
    }

    public List<Country> getAllCountry() {
        return jdbcTemplate.query("SELECT * FROM Country order by country_id", new BeanPropertyRowMapper<>(Country.class));
    }

    public List<Region> getAllRegion() {
        return jdbcTemplate.query("SELECT * FROM Region order by country_id", new BeanPropertyRowMapper<>(Region.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (passport, name, surname, country, region,sex, dob, doe, doi ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", person.getPassport().toUpperCase(Locale.ROOT), person.getName(), person.getSurname(), person.getCountry(), person.getRegion(), person.getSex(), person.getDob(), person.getDoe(), person.getDoi());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE Person SET passport=?, name=?, surname=?, country=?, region=?,sex=?, dob=?, doe=?, doi=? WHERE id=?", person.getPassport().toLowerCase(Locale.ROOT), person.getName(), person.getSurname(), person.getCountry(), person.getRegion(), person.getSex(), person.getDob(), person.getDoe(), person.getDoi(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)).stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public Person getPersonByPassport(String passport) {
        return jdbcTemplate.query("SELECT * FROM Person where passport like ?",
                new BeanPropertyRowMapper<>(Person.class), passport.toUpperCase(Locale.ROOT)).stream().findAny().orElse(null);
    }

    public List<FindRegion> getCIDByCName(String name) {
        if (name.endsWith("c1")) name = name.split("c1")[0] + " shahar";
        else if (name.endsWith("R1")) name = name.split("R1")[0] + " Resp.";
        else name = name.split("v1")[0] + " viloyati";

        return jdbcTemplate.query("SELECT c.country_id, c.c_name, r.code, r.r_name FROM Country c INNER JOIN Region r ON c.country_id = r.country_id where c.c_name like ?",
                new BeanPropertyRowMapper<>(FindRegion.class), name);
    }
}
