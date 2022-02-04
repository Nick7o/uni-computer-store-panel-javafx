package com.pobprojekt.javapobprojekt;

import com.pobprojekt.javapobprojekt.core.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        var dataController = new DataController();

        dataController.addItem(new CPU("Mach IV", "IntMD", 999.99, 2018, 3700.0, 8));
        dataController.addItem(new Mouse("F16", "MaxG3AR", 239.99, true, 16000, 6));
        dataController.addItem(new Mouse("F15", "MaxG3AR", 199.99, true, 10000, 4));
        dataController.addItem(new RAM("S-16G", "Cronos", 289.00, 2020, 16.0, 4133));
        dataController.addItem(new Disk("X1000", "VirtualMemory", 650.00, 2021, 1024, StorageType.SSD));

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        primaryStage.setTitle("Projekt");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
