module com.example.tap2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.jfr;


    opens com.example.tap2024 to javafx.fxml;
    exports com.example.tap2024;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;
    opens com.example.tap2024.models;
}