package com.example.tap2024.components;

import com.example.tap2024.models.ClienteModel;
import com.example.tap2024.vistas.FormCliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import java.util.Optional;

public class ButtonCell extends TableCell<ClienteModel, String> {
    Button btnCelda;

    public ButtonCell(String str) {
        btnCelda = new Button(str);
        btnCelda.setOnAction(event -> EventoBoton(str));
    }

    private void EventoBoton(String str) {
        ClienteModel objCte;
        objCte = this.getTableView().getItems().get(this.getIndex());
        if (str.equals("Editar")) {
            new FormCliente(this.getTableView(), objCte);
        }else{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Mesnaje del sistema :)");
            alerta.setContentText("¿Deseas eliminar el registro seleccionado?");
            Optional<ButtonType> opcion = alerta.showAndWait();
            if (opcion.get() == ButtonType.OK) {
                objCte.Delete();

                this.getTableView().setItems(objCte.Select());
                this.getTableView().refresh();

            }

        }
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b) {
            this.setGraphic(btnCelda);
        }
    }
}
