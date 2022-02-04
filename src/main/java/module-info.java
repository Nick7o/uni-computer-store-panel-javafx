module com.pobprojekt.javapobprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pobprojekt.javapobprojekt to javafx.fxml;
    exports com.pobprojekt.javapobprojekt;
    exports com.pobprojekt.javapobprojekt.gui;
    exports com.pobprojekt.javapobprojekt.core;
    opens com.pobprojekt.javapobprojekt.gui to javafx.fxml;
}