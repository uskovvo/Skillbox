package ru.skillbox;

public class RAM {
    private final RAMType type;
    private final int volume;
    private final double weight;

    public RAM(RAMType type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public RAMType getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public RAM setType(RAMType type) {
        return new RAM(type, volume, weight);
    }

    public RAM setVolume(int volume) {
        return new RAM(type, volume, weight);
    }

    public RAM setWeight(double weight) {
        return new RAM(type, volume, weight);
    }

    public String toString() {
        return  "\nОперативная память: " +
                "\nТип памяти: " + type +
                "\nОбъем памяти: " + volume + "Гб" +
                "\nВес: " + weight + "кг.\n";
    }
}
