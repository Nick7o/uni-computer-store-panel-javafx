package com.pobprojekt.javapobprojekt.gui;

import com.pobprojekt.javapobprojekt.DataController;
import com.pobprojekt.javapobprojekt.core.ClassType;
import com.pobprojekt.javapobprojekt.core.Product;
import com.pobprojekt.javapobprojekt.core.Upgradable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ListView extends View {
    private ObservableList<Product> listData;
    private FilteredList<Product> filteredList;

    private TableView<Product> tableView;

    // Product columns
    private TableColumn<Product, Long> idColumn;
    private TableColumn<Product, String> manufacturerColumn;
    private TableColumn<Product, String> nameColumn;
    private TableColumn<Product, Double> priceColumn;
    private TableColumn<Product, ClassType> typeColumn;

    private TableColumn<Product, Button> editColumn;
    private TableColumn<Product, Button> deleteColumn;
    private TableColumn<Product, Button> upgradeColumn;

    private TableColumn<Product, String> additionalInfoColumn;

    // Filters
    private Predicate<Product> nameFilter = p -> true;

    private boolean useProductTypeFilter;
    private Predicate<Product> productTypeFilter = p -> true;

    ListView(AnchorPane container) {
        super(container);

        findTableView();
    }

    private boolean findTableView() {
        tableView = new TableView<>();
        var classInfo = tableView.getClass();
        tableView = null;

        var foundTables = container.getChildren().stream()
                .filter(classInfo::isInstance)
                .map(x -> (TableView<Product>)x).collect(Collectors.toList());

        if (foundTables.size() == 0)
            return false;

        tableView = foundTables.get(0);
        tableView.getColumns().clear();
        return true;
    }

    public TableView<Product> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Product> tableView) {
        this.tableView = tableView;
    }

    public ObservableList<Product> getListData() {
        return listData;
    }

    public void setListData(ObservableList<Product> listData) {
        this.listData = listData;

        if (filteredList == null) {
            filteredList = new FilteredList<>(listData);
            tableView.setItems(filteredList);
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public void draw() {
        super.draw();

        createGenericColumns();
    }

    private void createGenericColumns() {
        idColumn = createColumn(idColumn, "Id", "id", 40, 40);
        manufacturerColumn = createColumn(manufacturerColumn, "Producent", "manufacturer");
        nameColumn = createColumn(nameColumn, "Nazwa", "name");
        priceColumn = createColumn(priceColumn, "Cena", "price");
        typeColumn = createColumn(typeColumn, "Rodzaj", "classType");
        additionalInfoColumn = createColumn(additionalInfoColumn, "Dodatkowe informacje", "AdditionalInfo", 300, 150, true);

        editColumn = createActionColumn(editColumn, "Edycja", "Edytuj", (Product p) -> {
            MenuController.getInstance().editProduct(p);
            return p;
        }, (Product p) -> true);
        deleteColumn = createActionColumn(deleteColumn, "Usuwanie", "Usuń", (Product p) -> {
            DataController.getInstance().removeItem(p);
            tableView.refresh();
            return p;
        }, (Product p) -> true);
        upgradeColumn = createActionColumn(upgradeColumn, "Ulepszanie", "Ulepsz", p -> {
            ((Upgradable)p).upgrade();
            tableView.refresh();
            return p;
        }, (Product p) -> p instanceof Upgradable);
    }

    public void setNameFilter(TextField nameFilterTextField) {
        nameFilterTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            nameFilter = p -> {
                if (newVal.isEmpty())
                    return true;

                return p.getName().contains(newVal) || p.getManufacturer().contains(newVal) || p.getAdditionalInfo().contains(newVal);
            };

            updateFilteredList();
        });
    }

    public void setProductTypeFilter(RadioButton useFilterRadioButton, ComboBox<ClassType> productTypeFilterComboBox) {
        useFilterRadioButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            useProductTypeFilter = newVal;
            updateFilteredList();
        });

        productTypeFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            productTypeFilter = p -> {
                return !useProductTypeFilter || p.getClassType() == newVal || p.getClassType().isChildOf(newVal);
            };

            updateFilteredList();
        });
    }

    public void updateFilteredList() {
        filteredList.setPredicate(nameFilter.and(productTypeFilter));
        var sortableData = new SortedList<Product>(filteredList);
        sortableData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortableData);
        tableView.refresh();
    }

    private <T> TableColumn<Product, T> createColumn(TableColumn<Product, T> column, String columnName, String propertyName) {
        return createColumn(column, columnName, propertyName, -1, -1);
    }

    private <T> TableColumn<Product, T> createColumn(TableColumn<Product, T> column, String columnName, String propertyName, int width, int minWidth) {
        if (column != null)
            return column;

        var newColumn = new TableColumn<Product, T>(columnName);

        if (minWidth > 0)
            newColumn.setMinWidth(minWidth);
        else
            newColumn.setMinWidth(50);

        if (width > 0)
            newColumn.setPrefWidth(width);
        else
            newColumn.setPrefWidth(105);

        newColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));

        tableView.getColumns().add((TableColumn<Product, ?>) newColumn);

        return newColumn;
    }

    private TableColumn<Product, String> createColumn(TableColumn<Product, String> column, String columnName, String propertyName, int width, int minWidth, boolean enableWrapping) {
        var newColumn = createColumn(column, columnName, propertyName, width, minWidth);

        newColumn.setCellFactory(new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
            @Override
            public TableCell<Product, String> call(TableColumn<Product, String> productTTableColumn) {
                return new TableCell<Product, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(productTTableColumn.getWidth() - 5);
                            this.setWrapText(true);

                            setGraphic(text);
                        }
                        else
                            setGraphic(null);
                    }
                };
            }
        });

        return newColumn;
    }

    private <S> TableColumn<S, Button> createActionColumn(TableColumn<S, Button> column, String columnName, String actionName, Function<S, S> action, Function<S, Boolean> getVisibility) {
        if (column != null)
            return column;

        var newColumn = new TableColumn<S, Button>(columnName);
        newColumn.setMinWidth(70);
        newColumn.setPrefWidth(70);
        newColumn.setCellFactory(ButtonTableCell.create(actionName, action, getVisibility));

        tableView.getColumns().add((TableColumn<Product, ?>) newColumn);

        return newColumn;
    }
}
