package aza.test.spring.testpeople.testpeople.web.models;

import java.util.Date;

public class Find {
    private String gender;
    private String country;
    private String region;
    private Date dob;

    public Find(String gender, String country, String region, Date dob) {
        this.gender = gender;
        this.country = country;
        this.region = region;
        this.dob = dob;
    }

    public Find() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
