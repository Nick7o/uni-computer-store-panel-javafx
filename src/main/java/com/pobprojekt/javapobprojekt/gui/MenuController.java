package com.pobprojekt.javapobprojekt.gui;

import com.pobprojekt.javapobprojekt.DataController;
import com.pobprojekt.javapobprojekt.core.ClassType;
import com.pobprojekt.javapobprojekt.core.Product;
import com.pobprojekt.javapobprojekt.core.Upgradable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.text.ParseException;

public class MenuController {
    private static MenuController instance;

    private MenuState menuState;

    // Views
    private ProductView productView;
    private ListView listView;

    @FXML private AnchorPane contentContainer;
    @FXML private AnchorPane tableContainer;

    @FXML private RadioButton filterUseProductTypeRadioButton;
    @FXML private ComboBox<ClassType> filterProductTypeComboBox;
    @FXML private TextField filterProductNameTextField;
    @FXML private TextField filterProductIdTextField;

    public MenuController() {
        if (instance != null)
            return;

        instance = this;
    }

    public static MenuController getInstance() {
        return instance;
    }

    public MenuState getState() {
        return menuState;
    }

    public boolean setState(MenuState menuState) {
        if (this.menuState == menuState)
            return false;

        this.menuState = menuState;
        clearGUI();

        switch (menuState) {
            case Start:
            case Edit:
                break;
            case Add:
                createAddObjectView();
                break;
            case List:
                createListView();
                break;
        }

        return true;
    }

    public void clearGUI() {
        contentContainer.setVisible(false);
        contentContainer.getChildren().clear();

        tableContainer.setVisible(false);
    }

    public void createAddObjectView() {
        productView = new ProductView(contentContainer);
        productView.draw();
    }

    public void createListView() {
        var createdNewView = false;

        if (listView == null) {
            listView = new ListView(tableContainer);
            createdNewView = true;
        }

        if (createdNewView) {
            filterProductTypeComboBox.getItems().setAll(ClassType.getClassTypes(true));
            filterProductTypeComboBox.setValue(filterProductTypeComboBox.getItems().get(0));
            listView.setProductTypeFilter(filterUseProductTypeRadioButton, filterProductTypeComboBox);
            listView.setNameFilter(filterProductNameTextField);
        }

        listView.setListData(DataController.getInstance().products);
        listView.updateFilteredList();
        listView.draw();
    }

    public void editProduct(Product product) {
        setState(MenuState.Edit);

        productView = new ProductView(contentContainer);
        productView.setEditingProduct(product);
    }

    public void onAddObjectPressed(ActionEvent event) {
        setState(MenuState.Add);
    }

    public void onShowListPressed(ActionEvent event) {
        setState(MenuState.List);
    }

    public void onUpgradeProductsPressed(ActionEvent event) {
        for (var product : DataController.getInstance().products) {
            if (product instanceof Upgradable)
                ((Upgradable) product).upgrade();
        }

        if (menuState == MenuState.List && listView != null)
            listView.getTableView().refresh();
    }

    public void onDeleteProductById(ActionEvent event) {
        try {
            var idToRemove = Integer.parseInt(filterProductIdTextField.getText());
            DataController.getInstance().products.removeIf(p -> p.getId() == idToRemove);

            if (menuState == MenuState.List && listView != null)
                listView.getTableView().refresh();
        }
        catch (NumberFormatException exception) {
            System.out.println("Caught exception: " + exception);
        }
    }
}
