package org.example;

public class Vendor {
    private String id;
    private String name;
    private String serviceType;

    private double pricing;

    public Vendor(){

    }
    public Vendor(String id, String name, String serviceType, double pricing) {
        this.id = id;
        this.name = name;
        this.serviceType = serviceType;
        this.pricing = pricing;

    }

    // Getters and setters
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }



    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }



    @Override
    public String toString() {
        return "Vendor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", pricing=" + pricing +
                '}';
    }
}
