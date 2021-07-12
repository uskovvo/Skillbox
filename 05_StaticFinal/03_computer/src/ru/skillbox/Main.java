package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        CPU cpu = new CPU(2.4, 4, "Intel", 0.3);
        RAM ram = new RAM(RAMType.DDR3, 8, 0.1);
        HDD hdd = new HDD(HDDType.SSD, 2, 0.5);
        Monitor monitor = new Monitor(32, MonitorType.IPS, 5);
        Keyboard keyboard = new Keyboard(KeyboardType.Gamers, KeyboardBacklight.YES, 0.7);
        Computer computer = new Computer("Vendor Company", "Venom", cpu, ram, hdd, monitor, keyboard);
        System.out.println(computer);
    }
}
