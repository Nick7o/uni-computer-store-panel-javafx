package com.pobprojekt.javapobprojekt.core;

public abstract class ComputerHardware extends Product {
    private int releaseYear;

    public ComputerHardware(String name, String manufacturer, double price, int releaseYear) {
        super(name, manufacturer, price);
        this.classType = ClassType.ComputerHardware;
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public abstract float getRating();

    @Override
    public String getAdditionalInfo() {
        return "Rok wydania: " + getReleaseYear() +
                ", ocena parametrów: " + String.format("%.1f", getRating());
    }
}
