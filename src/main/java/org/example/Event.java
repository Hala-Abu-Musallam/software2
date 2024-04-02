package org.example;

public class Event {

    private String name;
    private String date;

    private String time;
    private double price;
    private String vendorName;

    public Event(String name, String date, String time, double price, String vendorName) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.price = price;
        this.vendorName = vendorName;
    }

    // Getters and setters for event properties

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }





    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
          return name + "," + date + "," + time + "," + price + "," + vendorName;
    }
}