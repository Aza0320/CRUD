package aza.test.spring.testpeople.testpeople.web.dao;

import aza.test.spring.testpeople.testpeople.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Test> getTest(int offset, int next, String result) {
        StringBuilder startSql = new StringBuilder("SELECT * FROM Test Where ");
        String endSql = " order by id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";

        ArrayList<Object> allParam = new ArrayList<>();
        String[] arr = result.split(";");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("---")) {
                startSql.append("1=? ");
                if (i != 3) startSql.append("and ");
                allParam.add(1);
            } else {
                switch (i) {
                    case 0 -> {
                        startSql.append("sex=? and ");
                        allParam.add(arr[i]);
                    }
                    case 1 -> {
                        startSql.append("country=? and ");
                        allParam.add(arr[i].replaceAll(",", " "));
                    }
                    case 2 -> {
                        startSql.append("region=? and ");
                        allParam.add(arr[i].replaceAll(",", " "));
                    }
                    case 3 -> {
                        startSql.append("dob=? ");
                        allParam.add(arr[i]);
                    }
                }
            }
        }

        Object dob;
        try {
            dob = new SimpleDateFormat("dd.MM.yyyy").parse(allParam.get(3).toString());
        } catch (Exception ignored) {
            dob = 1;
        }
        return jdbcTemplate.query(startSql + endSql,
                new BeanPropertyRowMapper<>(Test.class), allParam.get(0), allParam.get(1),
                allParam.get(2), dob, offset, next);
    }

    public List<Count> getTotal() {
       return jdbcTemplate.query("SELECT COUNT (*) FROM Test", new BeanPropertyRowMapper<>(Count.class));
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
