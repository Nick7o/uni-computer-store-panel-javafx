package com.pobprojekt.javapobprojekt.gui;

import com.pobprojekt.javapobprojekt.DataController;
import com.pobprojekt.javapobprojekt.core.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ProductView extends View {
    public GridPane grid;

    // Product fields
    public ComboBox<ClassType> objectTypeComboBox;
    public TextField nameTextField;
    public TextField manufacturerTextField;
    public TextField priceTextField;

    // Peripheral fields
    public RadioButton hasUSBRadioButton;
    // Monitor
    public TextField resolutionXTextField;
    public TextField resolutionYTextField;
    public TextField diagonalTextField;
    // Mouse
    public TextField maxDPITextField;
    public TextField additionalButtonsTextField;

    // Computer Hardware
    public TextField releaseYearTextField;
    // Processor
    public TextField clockSpeedTextField;
    // CPU
    public TextField coresTextField;
    // GPU
    public TextField vramCapacityTextField;

    // Memory
    public TextField capacityTextField;
    // RAM
    public TextField memoryClockTextField;
    // Disk
    public ComboBox<StorageType> storageTypeComboBox;

    private Product editingProduct;

    public ProductView(AnchorPane container) {
        super(container);

        grid = ViewHelper.createContentGrid();
        container.getChildren().add(grid);
    }

    @Override
    public void reset() {
        resetView(true);
    }

    public void resetView(boolean resetClassType) {
        grid.getChildren().clear();

        if (resetClassType)
            objectTypeComboBox = null;

        if (nameTextField != null) nameTextField.setText("");
        if (manufacturerTextField != null) manufacturerTextField.setText("");
        if (priceTextField != null) priceTextField.setText("");

        // Peripheral fields
        if (hasUSBRadioButton != null) hasUSBRadioButton = null;
        // Monitor
        if (resolutionXTextField != null) resolutionXTextField.setText("");
        if (resolutionYTextField != null) resolutionYTextField.setText("");
        if (diagonalTextField != null) diagonalTextField.setText("");
        // Mouse
        if (maxDPITextField != null) maxDPITextField.setText("");
        if (additionalButtonsTextField != null) additionalButtonsTextField.setText("");

        // Comp. Hardware
        if (releaseYearTextField != null) releaseYearTextField.setText("");
        // Processor
        if (clockSpeedTextField != null) clockSpeedTextField.setText("");
        // CPU
        if (coresTextField != null) coresTextField.setText("");
        // GPU
        if (vramCapacityTextField != null) vramCapacityTextField.setText("");

        // Memory
        if (capacityTextField != null) capacityTextField.setText("");
        // RAM
        if (memoryClockTextField != null) memoryClockTextField.setText("");
        // Disk
        if (storageTypeComboBox != null) storageTypeComboBox.setValue(StorageType.HDD);
    }

    @Override
    public void draw() {
        super.draw();

        grid.getChildren().clear();

        drawObjectTypeComboBox(0, grid.getRowCount());
        drawMandatoryFields(0, grid.getRowCount());

        if (objectTypeComboBox.getValue() == null)
            return;

        var selectedClassType = objectTypeComboBox.getValue();

        // Drawing peripherals
        if (selectedClassType.isPeripheral()) {
            drawPeripheralFields(0, grid.getRowCount());

            if (selectedClassType == ClassType.Monitor)
                drawMonitorFields(0, grid.getRowCount());
            else if (selectedClassType == ClassType.Mouse)
                drawMouseFields(0, grid.getRowCount());
        }

        // Drawing computer hardware
        if (selectedClassType.isComputerHardware()) {
            drawComputerHardwareFields(0, grid.getRowCount());

            // Processors
            if (selectedClassType.isProcessor())
            {
                drawProcessorFields(0, grid.getRowCount());

                if (selectedClassType == ClassType.CPU)
                    drawCPUFields(0, grid.getRowCount());
                else if (selectedClassType == ClassType.GPU)
                    drawGPUFields(0, grid.getRowCount());
            }

            // Memory
            if (selectedClassType.isMemory())
            {
                drawMemoryFields(0, grid.getRowCount());

                if (selectedClassType == ClassType.RAM)
                    drawRAMFields(0, grid.getRowCount());
                else if (selectedClassType == ClassType.Disk)
                    drawDiskFields(0, grid.getRowCount());
            }
        }

        drawFinalizationMenu(0, grid.getRowCount() + 1);
    }

    public void drawObjectTypeComboBox(int column, int row){
        if (objectTypeComboBox == null) {
            objectTypeComboBox = ViewHelper.createClassComboBox(false);
            objectTypeComboBox.setValue(objectTypeComboBox.getItems().get(0));

            objectTypeComboBox.valueProperty().addListener((c, oldVal, newVal) -> {
                draw();
            });
        }

        addNodeToGrid(grid, "Typ produktu", objectTypeComboBox, column, row);
    }

    public void drawMandatoryFields(int column, int row){
        if (nameTextField == null)
            nameTextField = new TextField("Nazwa");
        if (manufacturerTextField == null)
            manufacturerTextField = new TextField("Producent");
        if (priceTextField == null)
            priceTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Nazwa", nameTextField, column + 0, row);
        addNodeToGrid(grid, "Producent", manufacturerTextField, column + 2, row);
        addNodeToGrid(grid, "Cena", priceTextField, column + 4, row);
    }

    public void drawPeripheralFields(int column, int row){
        if (hasUSBRadioButton == null)
            hasUSBRadioButton = new RadioButton();

        addNodeToGrid(grid, "Posiada USB?", hasUSBRadioButton, column, row);
    }

    public void drawMonitorFields(int column, int row) {
        if (resolutionXTextField == null)
            resolutionXTextField = ViewHelper.createIntTextField();
        if (resolutionYTextField == null)
            resolutionYTextField = ViewHelper.createIntTextField();
        if (diagonalTextField == null)
            diagonalTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Rozdzielczość X", resolutionXTextField, column + 0, row);
        addNodeToGrid(grid, "Rozdzielczość Y", resolutionYTextField, column + 2, row);
        addNodeToGrid(grid, "Przekątna", diagonalTextField, column + 4, row);
    }

    public void drawMouseFields(int column, int row) {
        if (maxDPITextField == null)
            maxDPITextField = ViewHelper.createIntTextField();
        if (additionalButtonsTextField == null)
            additionalButtonsTextField = ViewHelper.createIntTextField();

        addNodeToGrid(grid, "Maks. DPI", maxDPITextField, column + 0, row);
        addNodeToGrid(grid, "Dodatkowe przyciski", additionalButtonsTextField, column + 2, row);
    }

    public void drawComputerHardwareFields(int column, int row) {
        if (releaseYearTextField == null)
            releaseYearTextField = ViewHelper.createIntTextField();

        addNodeToGrid(grid, "Rok premiery", releaseYearTextField, column, row);
    }

    public void drawProcessorFields(int column, int row) {
        if (clockSpeedTextField == null)
            clockSpeedTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Zegar (MHz)", clockSpeedTextField, column, row);
    }

    public void drawCPUFields(int column, int row){
        if (coresTextField == null)
            coresTextField = ViewHelper.createIntTextField();

        addNodeToGrid(grid, "Liczba rdzeni", coresTextField, column, row);
    }

    public void drawGPUFields(int column, int row){
        if (vramCapacityTextField == null)
            vramCapacityTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Video RAM (MB)", vramCapacityTextField, column, row);
    }

    public void drawFinalizationMenu(int column, int row) {
        if (editingProduct != null ) {
            Button endEditingButton = new Button();
            endEditingButton.setText("Zapisz");
            endEditingButton.setOnAction(event -> {
                applyChanges(editingProduct);
                MenuController.getInstance().setState(MenuState.List);
            });

            grid.add(endEditingButton, column, row);
        }
        else {
            Button addButton = new Button();
            addButton.setText("Dodaj obiekt");
            addButton.setOnAction(event -> {
                createProduct();
            });

            grid.add(addButton, column, row);

            Button clearButton = new Button();
            clearButton.setText("Wyczyść dane");
            clearButton.setOnAction(event -> {
                resetView(false);
                draw();
            });

            grid.add(clearButton, column + 1, row);
        }
    }

    public void drawMemoryFields(int column, int row) {
        if (capacityTextField == null)
            capacityTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Pojemność (GB)", capacityTextField, column, row);
    }

    public void drawRAMFields(int column, int row) {
        if (memoryClockTextField == null)
            memoryClockTextField = ViewHelper.createDoubleTextField();

        addNodeToGrid(grid, "Częstotliwość pamięci (MHz)", memoryClockTextField, column, row);
    }

    public void drawDiskFields(int column, int row) {
        if (storageTypeComboBox == null) {
            storageTypeComboBox = ViewHelper.createComboBox(StorageType.values());
            storageTypeComboBox.setValue(StorageType.HDD);
        }

        addNodeToGrid(grid, "Rodzaj dysku", storageTypeComboBox, column, row);
    }

    public Product getEditingProduct() {
        return editingProduct;
    }

    public void setEditingProduct(Product product) {
        editingProduct = product;

        // Create combo box, so we can later set its value
        drawObjectTypeComboBox(0, grid.getRowCount());
        // Changing causes redraw and needed fields are instantiated
        objectTypeComboBox.setValue(ClassType.None);
        objectTypeComboBox.setValue(product.getClassType());

        grid.getChildren().remove(objectTypeComboBox);
        var firstNode = grid.getChildren().get(0);
        if (firstNode instanceof Label){
            ((Label) firstNode).setText("Edytujesz: " + product.getClassType().toString());
        }

        nameTextField.setText(product.getName());
        manufacturerTextField.setText(product.getManufacturer());
        priceTextField.setText(String.valueOf(product.getPrice()));

        if (editingProduct instanceof Peripheral)
            hasUSBRadioButton.setSelected(((Peripheral) product).getHasUSB());
        if (editingProduct instanceof Monitor) {
            resolutionXTextField.setText(String.valueOf(((Monitor) product).getResolutionX()));
            resolutionYTextField.setText(String.valueOf(((Monitor) product).getResolutionY()));
            diagonalTextField.setText(String.valueOf(((Monitor) product).getDiagonal()));
        }
        if (editingProduct instanceof Mouse) {
            maxDPITextField.setText(String.valueOf(((Mouse) product).getMaxDPI()));
            additionalButtonsTextField.setText(String.valueOf(((Mouse) product).getAdditionalButtons()));
        }

        if (editingProduct instanceof ComputerHardware)
            releaseYearTextField.setText(String.valueOf(((ComputerHardware)editingProduct).getReleaseYear()));
        if (editingProduct instanceof Processor)
            clockSpeedTextField.setText(String.valueOf(((Processor) editingProduct).getClockSpeed()));
        if (editingProduct instanceof CPU)
            coresTextField.setText(String.valueOf(((CPU) editingProduct).getCores()));
        if (editingProduct instanceof GPU)
            vramCapacityTextField.setText(String.valueOf(((GPU)editingProduct).getVramCapacity()));

        if (editingProduct instanceof Memory)
            capacityTextField.setText(String.valueOf(((Memory)editingProduct).getCapacity()));
        if (editingProduct instanceof RAM)
            memoryClockTextField.setText(String.valueOf(((RAM)editingProduct).getMemoryClock()));
        if (editingProduct instanceof Disk)
            storageTypeComboBox.setValue(((Disk)editingProduct).getStorageType());
    }

    public Product createProduct() {
        Product p = null;

        switch (objectTypeComboBox.getValue()) {
            case None:
            case Product:
            case Peripheral:
            case ComputerHardware:
            case Processor:
            case Memory:
                break;
            case Monitor:
                p = new Monitor(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        hasUSBRadioButton.isSelected(),
                        Integer.parseInt(resolutionXTextField.getText()),
                        Integer.parseInt(resolutionYTextField.getText()),
                        Float.parseFloat(diagonalTextField.getText()));
                break;
            case Mouse:
                p = new Mouse(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        hasUSBRadioButton.isSelected(),
                        Integer.parseInt(maxDPITextField.getText()),
                        Integer.parseInt(additionalButtonsTextField.getText()));
                break;
            case CPU:
                p = new CPU(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(releaseYearTextField.getText()),
                        Double.parseDouble(clockSpeedTextField.getText()),
                        Integer.parseInt(coresTextField.getText()));
                break;
            case GPU:
                p = new GPU(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(releaseYearTextField.getText()),
                        Double.parseDouble(clockSpeedTextField.getText()),
                        Double.parseDouble(vramCapacityTextField.getText()));
                break;
            case RAM:
                p = new RAM(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(releaseYearTextField.getText()),
                        Double.parseDouble(capacityTextField.getText()),
                        Double.parseDouble(memoryClockTextField.getText()));
                break;
            case Disk:
                p = new Disk(nameTextField.getText(),
                        manufacturerTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(releaseYearTextField.getText()),
                        Double.parseDouble(capacityTextField.getText()),
                        storageTypeComboBox.getValue());
                break;
        }

        DataController.getInstance().addItem(p);

        return p;
    }

    public void applyChanges(Product product) {
        product.setName(nameTextField.getText());
        product.setManufacturer(manufacturerTextField.getText());
        product.setPrice(Double.parseDouble(priceTextField.getText()));

        if (product instanceof Peripheral)
            ((Peripheral) product).setHasUSB(hasUSBRadioButton.isSelected());
        if (product instanceof Monitor) {
            ((Monitor) product).setResolutionX(Integer.parseInt(resolutionXTextField.getText()));
            ((Monitor) product).setResolutionY(Integer.parseInt(resolutionYTextField.getText()));
            ((Monitor) product).setDiagonal(Float.parseFloat(diagonalTextField.getText()));
        }
        if (product instanceof Mouse) {
            ((Mouse) product).setMaxDPI(Integer.parseInt(maxDPITextField.getText()));
            ((Mouse) product).setAdditionalButtons(Integer.parseInt(additionalButtonsTextField.getText()));
        }

        if (product instanceof ComputerHardware)
            ((ComputerHardware) product).setReleaseYear(Integer.parseInt(releaseYearTextField.getText()));
        if (product instanceof Processor)
            ((Processor) product).setClockSpeed(Double.parseDouble(clockSpeedTextField.getText()));
        if (product instanceof CPU)
            ((CPU) product).setCores(Integer.parseInt(coresTextField.getText()));
        if (product instanceof GPU)
            ((GPU) product).setVramCapacity(Double.parseDouble(vramCapacityTextField.getText()));

        if (product instanceof Memory)
            ((Memory) product).setCapacity(Double.parseDouble(capacityTextField.getText()));
        if (product instanceof RAM)
            ((RAM) product).setMemoryClock(Double.parseDouble(memoryClockTextField.getText()));
        if (product instanceof Disk)
            ((Disk) product).setStorageType(storageTypeComboBox.getValue());
    }

    public static void addNodeToGrid(GridPane grid, String labelText, Node node, int column, int row) {
        grid.add(new Label(labelText), column, row);
        grid.add(node, column + 1, row);
    }
}
