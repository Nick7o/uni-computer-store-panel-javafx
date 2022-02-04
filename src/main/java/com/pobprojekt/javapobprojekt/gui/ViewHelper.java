package com.pobprojekt.javapobprojekt.gui;

import com.pobprojekt.javapobprojekt.core.ClassType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViewHelper {
    public static ComboBox<ClassType> createClassComboBox(boolean displayAbstractTypes){
        ComboBox<ClassType> comboBox = new ComboBox<>();
        comboBox.getItems().setAll(ClassType.getClassTypes(displayAbstractTypes));

        comboBox.setPrefWidth(150.0);
        return comboBox;
    }

    public static <E> ComboBox<E> createComboBox(E[] values){
        ComboBox<E> comboBox = new ComboBox<>();
        comboBox.getItems().setAll(values);//Arrays.stream(values).collect(Collectors.toList()));

        comboBox.setPrefWidth(150.0);
        return comboBox;
    }

    public static GridPane createContentGrid() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        return grid;
    }

    public static TextField createDoubleTextField() {
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches())
                return c;
            else
                return null;
        };

        StringConverter<Double> converter = new StringConverter<Double>() {
            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s))
                    return 0.0;
                else
                    return Double.valueOf(s);
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        TextField textField = new TextField();
        textField.setTextFormatter(textFormatter);

        return textField;
    }

    public static TextField createIntTextField(){
        TextField textField = new TextField();
        textField.setText("0");

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }

                if (newValue.isEmpty())
                    textField.setText("0");
            }
        });

        return textField;
    }
}
