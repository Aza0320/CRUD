package aza.test.spring.testpeople.testpeople.web.models;

public class FindRegion {
    private int country_id;
    private String c_name;
    private int code;
    private String r_name;

    public FindRegion(int country_id, String c_name, int code, String r_name) {
        this.country_id = country_id;
        this.c_name = c_name;
        this.code = code;
        this.r_name = r_name;
    }

    public FindRegion() {

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }
}
