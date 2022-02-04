package com.pobprojekt.javapobprojekt.core;

public class GPU extends Processor {
    private double vramCapacity;

    public GPU(String name, String manufacturer, double price, int releaseYear, double clockSpeed, double vramCapacity) {
        super(name, manufacturer, price, releaseYear, clockSpeed);
        this.classType = ClassType.GPU;

        this.vramCapacity = vramCapacity;
    }

    public double getVramCapacity() {
        return vramCapacity;
    }

    public void setVramCapacity(double vramCapacity) {
        this.vramCapacity = vramCapacity;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", pamięć video: " + getVramCapacity() + " MB";
    }

    @Override
    public float getRating() {
        return Math.min(Math.max((float) ((vramCapacity / 16.0) * 10), 0), 10);
    }
}
