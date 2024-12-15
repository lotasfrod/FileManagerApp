module com.java.FileManager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.java.FileManager to javafx.fxml;
    exports com.java.FileManager;


}