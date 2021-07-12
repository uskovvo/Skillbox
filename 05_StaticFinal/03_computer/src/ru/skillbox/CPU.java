package ru.skillbox;

public class CPU {
    private final double frequency;
    private final int numbersOfCores;
    private final String manufacturer;
    private final double weight;

    public CPU(double frequency, int numbersOfCores, String manufacturer, double weight) {
        this.frequency = frequency;
        this.numbersOfCores = numbersOfCores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getNumbersOfCores() {
        return numbersOfCores;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public CPU setFrequency(double frequency) {
        return new CPU(frequency, numbersOfCores, manufacturer, weight);
    }

    public CPU setNumbersOfCores(int numbersOfCores) {
        return new CPU(frequency, numbersOfCores, manufacturer, weight);
    }

    public CPU setManufacturer(String manufacturer) {
        return new CPU(frequency, numbersOfCores, manufacturer, weight);
    }

    public CPU setWeight(double weight) {
        return new CPU(frequency, numbersOfCores, manufacturer, weight);
    }

    public String toString() {
        return  "\nПроцессор: " +
                "\nЧастота процессора: " + frequency + "ГГц" +
                "\nКоличество ядер: " + numbersOfCores  +
                "\nПроизводитель: " + manufacturer +
                "\nВес: " + weight + "кг.\n";
    }
}
