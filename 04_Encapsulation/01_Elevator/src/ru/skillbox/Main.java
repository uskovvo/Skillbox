package ru.skillbox;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Elevator elevator = new Elevator(-3, 26);

        while(true) {
            System.out.println("Лифт на: " + elevator.getCurrentFloor() + " этаже.\n");

            System.out.print("Введите номер этажа: ");

            int floor = new Scanner(System.in).nextInt();

            elevator.move(floor);

        }
    }
}
