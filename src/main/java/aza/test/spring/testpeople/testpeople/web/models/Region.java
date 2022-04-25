package aza.test.spring.testpeople.testpeople.web.models;

public class Region {
    private int country_id;
    private int code;
    private String r_name;

    public Region() {
    }

    public Region(int country_id, int code, String name) {
        this.country_id = country_id;
        this.code = code;
        this.r_name = name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String name) {
        this.r_name = name;
    }
}
