package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(56, 42, 115);
        CargoInfo cargoInfo = new CargoInfo(dimensions, 152, "sdfsdf",
                                        true, "dfg464", true);
        System.out.println(cargoInfo);
    }
}
