package org.example;
public class Vendor {
    private String name;
    private String serviceType;
    private String email;
    private int time;
    private int date;

    public Vendor() {

    }

    public Vendor(String name, String serviceType, String email, int time, int date) {
        this.name = name;
        this.serviceType = serviceType;
        this.email = email;
        this.time = time;
        this.date = date;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                ", date=" + date ;

    }
}
