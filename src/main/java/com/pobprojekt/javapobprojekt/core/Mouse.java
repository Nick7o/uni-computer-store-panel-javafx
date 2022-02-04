package com.pobprojekt.javapobprojekt.core;

public class Mouse extends Peripheral implements Upgradable {
    private int maxDPI;
    private int additionalButtons;

    public Mouse(String name, String manufacturer, double price, boolean hasUSB, int maxDPI, int additionalButtons) {
        super(name, manufacturer, price, hasUSB);
        this.classType = ClassType.Mouse;
        this.maxDPI = maxDPI;
        this.additionalButtons = additionalButtons;
    }

    public int getMaxDPI() {
        return maxDPI;
    }

    public void setMaxDPI(int maxDPI) {
        this.maxDPI = maxDPI;
    }

    public int getAdditionalButtons() {
        return additionalButtons;
    }

    public void setAdditionalButtons(int additionalButtons) {
        this.additionalButtons = additionalButtons;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", maks. DPI: " + getMaxDPI() +
                ", dodatkowe przyciski: " + getAdditionalButtons();
    }

    @Override
    public void upgrade() {
        maxDPI += 400;
        additionalButtons++;
    }
}
