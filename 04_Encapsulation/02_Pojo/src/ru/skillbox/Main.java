package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("Renault", "Kaptur");
        car.setColor("White");
        car.setMaxSpeed(200);
        car.setSizeWheels(17);

        System.out.println(car.getColor());
        System.out.println(car.getMaxSpeed());
        System.out.println(car.getSizeWheels());
    }
}
