package org.example.entities;

public class Speaker {
    private String brandName;
    private double price;

    public Speaker(String brandName, double price) {
        this.brandName = brandName;
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
