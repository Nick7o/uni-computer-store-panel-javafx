package com.pobprojekt.javapobprojekt.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.function.Function;

public class ButtonTableCell<T> extends TableCell<T, Button> {
    private Button button;
    private Function<T, Boolean> getVisibility;

    public ButtonTableCell(String name, Function<T, T> action, Function<T, Boolean> getVisibility) {
        this.getVisibility = getVisibility;

        button = new Button(name);
        button.setOnAction(e -> action.apply(getCurrentItem()));
    }

    public T getCurrentItem() {
        return getTableView().getItems().get(getIndex());
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || !getVisibility.apply(getCurrentItem()))
            setGraphic(null);
        else
            setGraphic(button);
    }

    public static <T> Callback<TableColumn<T, Button>, TableCell<T, Button>> create(String name, Function<T, T> action, Function<T, Boolean> getVisibility) {
        return (TableColumn<T, Button> x) -> new ButtonTableCell<>(name, action, getVisibility);
    }
}
