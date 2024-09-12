package org.example.entities;

import org.example.constants.Brands;

public class Tyre {
    private Brands tyreBrandName;
    private double tyrePrice;

    public Tyre(Brands tyreBrandName, double tyrePrice) {
        this.tyreBrandName = tyreBrandName;
        this.tyrePrice = tyrePrice;
    }

    public Brands getBrandName() {
        return tyreBrandName;
    }

    public void setBrandName(Brands tyreBrandName) {
        this.tyreBrandName = tyreBrandName;
    }

    public double getPrice() {
        return tyrePrice;
    }

    public void setPrice(double tyrePrice) {
        this.tyrePrice = tyrePrice;
    }
}
