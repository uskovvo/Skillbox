package ru.skillbox;

public class Product {
    private final String name;
    private int price;
    private final char barCode;

    public Product (String name, char barCode) {
        this.name = name;
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBarCode() {
        return barCode;
    }
}
