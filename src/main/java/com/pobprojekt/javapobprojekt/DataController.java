package com.pobprojekt.javapobprojekt;

import com.pobprojekt.javapobprojekt.core.CPU;
import com.pobprojekt.javapobprojekt.core.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DataController {
    private static DataController instance;

    public ObservableList<Product> products = FXCollections.observableArrayList();

    DataController() {
        if (instance != null)
            return;

        instance = this;
    }

    public static DataController getInstance() {
        return instance;
    }

    public void addItem(Product product) {
        products.add(product);
    }

    public boolean removeItem(Product product) {
        return products.remove(product);
    }
}
