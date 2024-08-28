package com.example.tap2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {

    private Button btn1, btn2, btn3;
    private VBox vbox;
    private HBox hbox;

    public void CrearUI8(){
        btn1 = new Button("boton 1");
        btn2 = new Button("Boton 2");
        btn3 = new Button("Boton 3");
        vbox = new VBox(btn1, btn2, btn3);
        hbox = new HBox(btn1, btn2, btn3);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        CrearUI8();
        Scene scene = new Scene(hbox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}