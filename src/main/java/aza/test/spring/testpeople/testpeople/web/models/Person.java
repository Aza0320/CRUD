package aza.test.spring.testpeople.testpeople.web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    @NotEmpty(message = "Passport is must not be empty")
    @Size(max = 9, min = 9, message = "Passport length is must be 9")
    private String passport;

    @NotEmpty(message = "Name is must not be empty")
    private String name;
    private int id;
    private String surname;
    private String address;
    private String sex;
    private Date doi;
    private Date doe;
    private Date dob;
    private String toDob;
    private String toDoe;
    private String toDoi;

    public Person(String passport, String name, int id, String surname, String address, String sex, Date doi, Date doe, Date dob, String toDob, String toDoe, String toDoi) {
        this.passport = passport;
        this.name = name;
        this.id = id;
        this.surname = surname;
        this.address = address;
        this.sex = sex;
        this.doi = doi;
        this.doe = doe;
        this.dob = dob;
        this.toDob = toDob;
        this.toDoe = toDoe;
        this.toDoi = toDoi;
    }

    public Person() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPol() {
        return sex;
    }

    public void setPol(String pol) {
        this.sex = pol;
    }

    public Date getDoi() {
        return doi;
    }

    public void setDoi(Date doi) {
        this.doi = doi;
    }

    public Date getDoe() {
        return doe;
    }

    public void setDoe(Date doe) {
        this.doe = doe;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public Date getDob() {
        return dob;
    }

    public String getDobString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(dob);
    }

    public String getDoeString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(doe);
    }

    public String getDoiString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(doi);
    }

    public void toDoe(String doe) throws ParseException {
        this.dob = new SimpleDateFormat("dd.MM.yyyy").parse(doe);;
    }

    public void toDoi(String doi) throws ParseException {
        this.dob = new SimpleDateFormat("dd.MM.yyyy").parse(doi);;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToDoe() {
        return toDoe;
    }

    public String getToDob() {
        return toDob;
    }

    public String getToDoi() {
        return toDoi;
    }

    public void setToDoe(String toDoe) {
        this.toDoe = toDoe;
        try {
            this.doe = new SimpleDateFormat("dd.MM.yyyy").parse(toDoe);
        } catch (ParseException ignored) {
        }
    }

    public void setToDoi(String toDoi) {
        this.toDoi = toDoi;
        try {
            this.doi = new SimpleDateFormat("dd.MM.yyyy").parse(toDoi);
        } catch (ParseException ignored) {
        }
    }

    public void setToDob(String toDob) {
        this.toDob = toDob;
        try {
            this.dob = new SimpleDateFormat("dd.MM.yyyy").parse(toDob);
        } catch (ParseException ignored) {
        }
    }
}
