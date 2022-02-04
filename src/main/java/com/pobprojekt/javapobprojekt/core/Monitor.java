package com.pobprojekt.javapobprojekt.core;

public class Monitor extends Peripheral {
    private int resolutionX, resolutionY;
    private float diagonal;

    public Monitor(String name, String manufacturer, double price, boolean hasUSB, int resolutionX, int resolutionY, float diagonal) {
        super(name, manufacturer, price, hasUSB);
        this.classType = ClassType.Monitor;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.diagonal = diagonal;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public float getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(float diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", rodzielczość: " + getResolutionX() + "x" + getResolutionY() +
                ", przekątna: " + getDiagonal() + "\"";
    }
}
