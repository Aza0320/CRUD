package aza.test.spring.testpeople.testpeople.web.models;

public class Count {
    private int total;

    public Count() {
    }

    public Count(int count) {
        this.total = count;
    }

    public int getCount() {
        return total;
    }

    public void setCount(int count) {
        this.total = count;
    }
}
