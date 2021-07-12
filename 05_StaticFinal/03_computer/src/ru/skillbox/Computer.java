package ru.skillbox;

public class Computer {
    private final String vendor;
    private final String name;
    private final CPU cpu;
    private final RAM ram;
    private final HDD hdd;
    private final Monitor monitor;
    private final Keyboard keyboard;

    private float weightComputer;

    public Computer(String vendor, String name, CPU cpu, RAM ram, HDD hdd, Monitor monitor, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        this.monitor = monitor;
        this.keyboard = keyboard;
        calculateWeight();
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public HDD getHdd() {
        return hdd;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public double getWeightComputer() {

        return weightComputer;
    }

    public Computer setVendor(String vendor) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setName(String name) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setCpu(CPU cpu) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setRam(RAM ram) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setHdd(HDD hdd) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setMonitor(Monitor monitor) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(vendor, name, cpu, ram, hdd, monitor, keyboard);
    }

    public void calculateWeight() {
        weightComputer = (float)(weightComputer + cpu.getWeight() + ram.getWeight() +
                         hdd.getWeight() + monitor.getWeight() + keyboard.getWeight());
    }

    public String toString() {
        return "Конфигурация вашего компьютера: \n" +
                "\nПроизводитель: " + vendor +
                "\nНазвание: " + name +
                cpu + ram + hdd + monitor + keyboard +
                "\nВес компьютера: " + weightComputer + "кг.\n";
    }
}
