package com.pobprojekt.javapobprojekt.core;

public class CPU extends Processor implements Upgradable {
    private int cores;

    public CPU(String name, String manufacturer, double price, int releaseYear, double clockSpeed, int cores) {
        super(name, manufacturer, price, releaseYear, clockSpeed);
        this.classType = ClassType.CPU;

        this.cores = cores;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", rdzenie: " + getCores();
    }

    @Override
    public float getRating() {
        return Math.min(Math.max((cores / 16.0f) * 10, 0), 10);
    }

    @Override
    public void upgrade() {
        cores += 2;
    }
}
