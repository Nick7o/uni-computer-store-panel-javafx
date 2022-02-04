package com.pobprojekt.javapobprojekt.core;

public abstract class Processor extends ComputerHardware {
    private double clockSpeed;

    public Processor(String name, String manufacturer, double price, int releaseYear, double clockSpeed) {
        super(name, manufacturer, price, releaseYear);
        this.classType = ClassType.Processor;

        this.clockSpeed = clockSpeed;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", częstotliwość zegara: " + getClockSpeed() + " MHz";
    }
}
