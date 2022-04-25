package aza.test.spring.testpeople.testpeople.web.models;

public class Country {
    private int country_id;
    private String c_name;

    public Country() {
    }

    public Country(int country_id, String c_name) {
        this.country_id = country_id;
        this.c_name = c_name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
