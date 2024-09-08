package com.example.tap2024;

import com.example.tap2024.vistas.Calculadora;
import com.example.tap2024.vistas.Loteria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {

    private BorderPane bdpPrincipal;
    private MenuBar mnbPrincipal;
    private Menu menComp1, menComp2, menSalir;
    private MenuItem mitCalculadora, mitLoteria;

    public void CrearUI8(){

        mitCalculadora = new MenuItem("Calculadora");
        mitLoteria = new MenuItem("Loteria");
        mitCalculadora.setOnAction(actionEvent -> new Calculadora());
        mitLoteria.setOnAction(actionEvent -> new Loteria());
        menComp1 = new Menu("Competencia 1");
        menComp1.getItems().add(mitCalculadora);
        menComp1.getItems().add(mitLoteria);
        mnbPrincipal = new MenuBar(menComp1);
        bdpPrincipal = new BorderPane();
        bdpPrincipal.setTop(mnbPrincipal);

    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        CrearUI8();
        Scene scene = new Scene(bdpPrincipal, 320, 240);
        scene.getStylesheets().add(getClass().getResource("/Styles/Main.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}