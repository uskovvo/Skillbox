package ru.skillbox;

public class Dimensions {
    private final int length;
    private final int width;
    private final int height;

    public Dimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public float volumeOfCargo() {

        return (float) (getLength() * getWidth() * getHeight()) / 1000000;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimensions setLength(int length) {
        return new Dimensions(length, width, height);
    }

    public Dimensions setWidth(int width) {
        return new Dimensions(length, width, height);
    }

    public Dimensions setHeight(int height) {
        return new Dimensions(length, width, height);
    }

    public String toString() {
        return "Габариты груза Д/Ш/В: " + length + "/" + width + "/" + height;
    }
}
