package com.pobprojekt.javapobprojekt.core;

public abstract class Memory extends ComputerHardware {
    private double capacity;

    public Memory(String name, String manufacturer, double price, int releaseYear, double capacity) {
        super(name, manufacturer, price, releaseYear);
        this.classType = ClassType.Memory;

        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", pojemność: " + String.format("%.1f", getCapacity()) + " GB";
    }
}
