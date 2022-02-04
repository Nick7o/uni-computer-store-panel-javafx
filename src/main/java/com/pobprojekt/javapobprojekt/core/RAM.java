package com.pobprojekt.javapobprojekt.core;

public class RAM extends Memory implements Upgradable {
    private double memoryClock;

    public RAM(String name, String manufacturer, double price, int releaseYear, double capacity, double memoryClock) {
        super(name, manufacturer, price, releaseYear, capacity);
        this.classType = ClassType.RAM;

        this.memoryClock = memoryClock;
    }

    public double getMemoryClock() {
        return memoryClock;
    }

    public void setMemoryClock(double memoryClock) {
        this.memoryClock = memoryClock;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", zegar pamięci: " + getMemoryClock() + " MHz";
    }

    @Override
    public float getRating() {
        return Math.min(Math.max(5 + (float)(getMemoryClock() * 0.0005f) + (float) ((getCapacity() / 16.0) * 2), 0), 10);
    }

    @Override
    public void upgrade() {
        memoryClock += 100;
    }
}
