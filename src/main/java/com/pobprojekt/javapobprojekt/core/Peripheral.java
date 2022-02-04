package com.pobprojekt.javapobprojekt.core;

public abstract class Peripheral extends Product {
    private boolean hasUSB;

    public Peripheral(String name, String manufacturer, double price, boolean hasUSB) {
        super(name, manufacturer, price);
        this.classType = ClassType.Peripheral;
        this.hasUSB = hasUSB;
    }

    public boolean getHasUSB() {
        return hasUSB;
    }

    public void setHasUSB(boolean hasUSB) {
        this.hasUSB = hasUSB;
    }

    @Override
    public String getAdditionalInfo() {
        return getHasUSB() ? "Posiada USB" : "Nie posiada USB";
    }
}
