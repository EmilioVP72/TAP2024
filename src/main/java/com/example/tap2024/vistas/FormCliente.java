package com.example.tap2024.vistas;

import com.example.tap2024.models.ClienteModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class FormCliente  extends Stage {

    private TextField txtNomCte;
    private TextField txtCorrCte;
    private TextField txtTelCte;
    private Button btnGuardar;
    private VBox vBox;
    private ClienteModel objCte;
    private Scene escena;

    public FormCliente() {
        objCte = new ClienteModel();
        CrearUI();
        this.setTitle("Agregar Cliente :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtNomCte = new TextField();
        txtNomCte.setPromptText("Ingrese su Nombre Completo :)");
        txtCorrCte = new TextField();
        txtCorrCte.setPromptText("Ingrese su Correo :)");
        txtTelCte = new TextField();
        txtTelCte.setPromptText("Ingrese su Telefone :)");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> GuardarCliente);
        vBox = new VBox(txtNomCte, txtCorrCte, txtTelCte, btnGuardar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 150, 150);
    }






}
