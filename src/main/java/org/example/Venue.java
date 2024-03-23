package org.example;

public class Venue {
    private String id;
    private String name;
    private String ownerName;
    private String location;
    private int capacity;
    private double pricing;

    public Venue() {
    }
    public Venue( String name, String ownerName, String location, int capacity, double pricing) {

        this.name = name;
        this.ownerName = ownerName;
        this.location = location;
        this.capacity = capacity;
        this.pricing = pricing;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    // toString method to represent the Venue details
    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", pricing=" + pricing +
                '}';
    }
}