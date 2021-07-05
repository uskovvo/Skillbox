package ru.skillbox;

public class Car {
    private String nameBrand;
    private String model;
    private int maxSpeed;
    private String color;
    private int sizeWheels;

    public Car(String nameBrand, String model) {
        this.nameBrand = nameBrand;
        this.model = model;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSizeWheels() {
        return sizeWheels;
    }

    public void setSizeWheels(int sizeWheels) {
        this.sizeWheels = sizeWheels;
    }
}
