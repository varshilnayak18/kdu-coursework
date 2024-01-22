package org.example.entities;

public class Vehicle {
    private Speaker speaker;
    private Tyre tyre;
    private double price;

    public Vehicle(Speaker speaker, Tyre tyre, double price) {
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice(){
        return speaker.getPrice()+tyre.getPrice()+price;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }
}
