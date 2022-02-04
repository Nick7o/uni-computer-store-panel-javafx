package com.pobprojekt.javapobprojekt.gui;

import javafx.scene.layout.AnchorPane;

public abstract class View {
    protected AnchorPane container;

    View (AnchorPane container) {
        this.container = container;
    }

    public abstract void reset();

    public void draw() {
        container.setVisible(true);
    }
}
