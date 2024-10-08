package com.example.tap2024.vistas;

import com.example.tap2024.models.ClienteModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.Buffer;

public class ListaClientes extends Stage {

    private TableView<ClienteModel> tbvClientes;
    private ToolBar tlbMenu;
    private VBox vbox;
    private Scene escena;;

    public ListaClientes(){
        CrearUI();
        this.setTitle("Lista de Clientes :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tlbMenu = new ToolBar();
        ImageView imv = new ImageView(getClass().getResource("/Images/siguiente.png").toString());
        Button btn_add_cliente = new Button();
        btn_add_cliente.setOnAction(actionEvent -> new FormCliente());
        btn_add_cliente.setGraphic(imv);
        tlbMenu.getItems().add(btn_add_cliente);

        tbvClientes = new TableView<>();
        vbox = new VBox(tlbMenu, tbvClientes);
        escena = new Scene(vbox, 500, 250);


    }
}
