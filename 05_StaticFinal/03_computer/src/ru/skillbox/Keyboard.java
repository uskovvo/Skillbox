package ru.skillbox;

public class Keyboard {
    private final KeyboardType type;
    private final KeyboardBacklight backlight;
    private final double weight;

    public Keyboard(KeyboardType type, KeyboardBacklight backlight, double weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public KeyboardBacklight getBacklight() {
        return backlight;
    }

    public double getWeight() {
        return weight;
    }

    public Keyboard setType(KeyboardType type) {
        return new Keyboard(type, backlight, weight);
    }

    public Keyboard setBacklight(KeyboardBacklight backlight) {
        return new Keyboard(type, backlight, weight);
    }

    public Keyboard setWeight(double weight) {
        return new Keyboard(type, backlight, weight);
    }

    public String toString() {
        return  "\nКлавиатура: " +
                "\nТип клавиатура: " + type +
                "\nНаличие подсветки: " + backlight +
                "\nВес: " + weight + "кг.\n";
    }
}
