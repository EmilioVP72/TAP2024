package com.example.tap2024.components;

import com.example.tap2024.models.ClienteModel;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<ClienteModel, String> {
    Button btnCelda;


    public ButtonCell(String str) {
        btnCelda = new Button(str);
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b) {
            this.setGraphic(btnCelda);
        }
    }
}
