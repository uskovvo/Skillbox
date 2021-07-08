package ru.skillbox;

public class CargoInfo {
    private final Dimensions dimensions;
    private final double weight;
    private final String address;
    private final boolean canFlipped;
    private final String regNumber;
    private final boolean fragile;

    public CargoInfo(Dimensions dimensions, double weight, String address, boolean canFlipped,
                     String regNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.address = address;
        this.canFlipped = canFlipped;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public boolean isCanFlipped() {
        return canFlipped;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String toString() {
        return "Информация о грузе:\n" +
                dimensions + "\n" +
                "Объем: " + dimensions.volumeOfCargo() + "куб. м." + "\n" +
                "Вес: " + getWeight();
    }
}
