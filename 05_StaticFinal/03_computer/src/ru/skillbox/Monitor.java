package ru.skillbox;

public class Monitor {
    private final double diagonal;
    private final MonitorType type;
    private final double weight;

    public Monitor(double diagonal, MonitorType type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public Monitor setDiagonal(double diagonal) {
        return new Monitor(diagonal, type, weight);
    }

    public MonitorType getType() {
        return type;
    }

    public Monitor setType(MonitorType type) {
        return new Monitor(diagonal, type, weight);
    }

    public double getWeight() {
        return weight;
    }

    public Monitor setWeight(double weight) {
        return new Monitor(diagonal, type, weight);
    }

    public String toString() {
        return  "\nМонитор: " +
                "\nДиагональ: " + diagonal +
                "\nМатрица: " + type +
                "\nВес: " + weight + "кг.\n";
    }
}
