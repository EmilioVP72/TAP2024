package com.example.tap2024.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Stage {

    private TextField txtNormal, txtAdmin;
    private PasswordField pwdAdmin;
    private Button btnNormal, btnAdmin;
    private Label lblNormal, lblAdmin;
    private VBox vboxNormal, vboxAdmin;
    private HBox hboxMain;
    private Scene escena;

    public Login() {
        crearUI();
        this.setTitle("Login :D");
        this.setScene(escena);
        this.show();
    }

    private void crearUI() {
        // Estilo de fuente
        Font font = new Font("Arial", 14);

        // Usuario Normal
        lblNormal = new Label("Usuario Normal");
        lblNormal.setFont(font);
        lblNormal.setTextFill(Color.WHITE);

        txtNormal = new TextField();
        txtNormal.setPromptText("Ingrese usuario");

        btnNormal = new Button("Ingresar como Usuario Normal");
        btnNormal.setFont(font);
        btnNormal.setStyle("-fx-background-color: #5A4FCF; -fx-text-fill: white;");
        btnNormal.setOnAction(actionEvent -> {
            String nombreCliente = txtNormal.getText();
            new MenuCliente(nombreCliente);
        });

        vboxNormal = new VBox(10, lblNormal, txtNormal, btnNormal);
        vboxNormal.setAlignment(Pos.CENTER); // Centramos los elementos
        vboxNormal.setSpacing(10);

        // Administrador
        lblAdmin = new Label("Administrador");
        lblAdmin.setFont(font);
        lblAdmin.setTextFill(Color.WHITE);

        txtAdmin = new TextField();
        txtAdmin.setPromptText("Ingrese admin");

        pwdAdmin = new PasswordField();
        pwdAdmin.setPromptText("Ingrese contraseña");

        btnAdmin = new Button("Ingresar como Admin");
        btnAdmin.setFont(font);
        btnAdmin.setStyle("-fx-background-color: #4F4FCF; -fx-text-fill: white;");
        btnAdmin.setDisable(true);

        pwdAdmin.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("123")) {
                btnAdmin.setDisable(false);
                btnAdmin.setOnAction(actionEvent -> {
                    String nombreAdmin = txtAdmin.getText();
                    new MenuAdmin(nombreAdmin);
                });
            } else {
                btnAdmin.setDisable(true);
            }
        });

        vboxAdmin = new VBox(10, lblAdmin, txtAdmin, pwdAdmin, btnAdmin);
        vboxAdmin.setAlignment(Pos.CENTER); // Centramos los elementos
        vboxAdmin.setSpacing(10);

        // Caja principal
        hboxMain = new HBox(20, vboxNormal, vboxAdmin);
        hboxMain.setAlignment(Pos.CENTER); // Centramos todo el contenido

        // Estilo de fondo y dimensiones
        hboxMain.setStyle("-fx-background-color: linear-gradient(to bottom, #7B68EE, #483D8B);");

        // Creamos la escena
        escena = new Scene(hboxMain, 400, 250);
    }
}
