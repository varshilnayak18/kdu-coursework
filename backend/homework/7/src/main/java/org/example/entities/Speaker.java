package org.example.entities;

import org.example.constants.Brands;

public class Speaker {
    private Brands speakerBrandName;
    private double speakerPrice;

    public Speaker(Brands speakerBrandName, double speakerPrice) {
        this.speakerBrandName = speakerBrandName;
        this.speakerPrice = speakerPrice;
    }

    public Brands getBrandName() {
        return speakerBrandName;
    }

    public void setBrandName(Brands speakerBrandName) {
        this.speakerBrandName = speakerBrandName;
    }

    public double getPrice() {
        return speakerPrice;
    }

    public void setPrice(double speakerPrice) {
        this.speakerPrice = speakerPrice;
    }
}
