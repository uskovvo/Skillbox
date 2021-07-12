package ru.skillbox;

public class HDD {
    private final HDDType type;
    private final int volume;
    private final double weight;

    public HDD(HDDType type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public HDDType getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public HDD setType(HDDType type) {
        return new HDD(type, volume, weight);
    }

    public HDD setVolume(int volume) {
        return new HDD(type, volume, weight);
    }

    public HDD setWeight(double weight) {
        return new HDD(type, volume, weight);
    }

    public String toString() {
        return  "\nЖесткий диск: " +
                "\nТип жесткого диска: " + type +
                "\nОбъем памяти жесткого диска: " + volume + "Тб" +
                "\nВес: " + weight + "кг.\n";
    }
}
