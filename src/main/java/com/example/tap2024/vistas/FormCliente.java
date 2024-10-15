package com.example.tap2024.vistas;

import com.example.tap2024.models.ClienteModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
    private TableView<ClienteModel> tbvCliente;
    private Scene escena;

    public FormCliente(TableView<ClienteModel> tbv, ClienteModel objC) {
        this.tbvCliente = tbv;
        CrearUI();
        if (objC != null) {
            this.objCte = objC;
            txtNomCte.setText(objCte.getCliente());
            txtCorrCte.setText(objCte.getCorreo());
            txtTelCte.setText(objCte.getTelefono());
            this.setTitle("Editar Cliente :)");
        }else{
            this.objCte = new ClienteModel();
            this.setTitle("Agregar Cliente :)");
        }
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
        btnGuardar.setOnAction(actionEvent -> GuardarCliente());
        vBox = new VBox(txtNomCte, txtCorrCte, txtTelCte, btnGuardar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 150, 150);
    }

    private void GuardarCliente(){
        objCte.setCliente(txtNomCte.getText());
        objCte.setTelefono(txtTelCte.getText());
        objCte.setCorreo(txtCorrCte.getText());
        String msj;
        Alert.AlertType type;

        if (objCte.getId_cliente() > 0) {
            objCte.Update();

        }else{
            if (objCte.Insert() > 0){
                msj = "Registro Insertado";
                type = Alert.AlertType.INFORMATION;

            }else{
                msj = "No Registro Insertado, intente de nuevo";
                type = Alert.AlertType.ERROR;

            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Mensaje del Sistema :)");
            alerta.setContentText(msj);
            alerta.showAndWait();
        }

        tbvCliente.setItems(objCte.Select());
        tbvCliente.refresh();

    }






}
