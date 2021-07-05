package ru.skillbox;

public class Country {
    private String name;
    private int sizePopulation;
    private int area;
    private String nameCapital;
    private boolean hasSeaAccess;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSizePopulation() {
        return sizePopulation;
    }

    public void setSizePopulation(int sizePopulation) {
        this.sizePopulation = sizePopulation;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getNameCapital() {
        return nameCapital;
    }

    public void setNameCapital(String nameCapital) {
        this.nameCapital = nameCapital;
    }

    public boolean isHasSeaAccess() {
        return hasSeaAccess;
    }

    public void setHasSeaAccess(boolean hasSeaAccess) {
        this.hasSeaAccess = hasSeaAccess;
    }
}
