package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCell;
import com.example.tap2024.models.ClienteModel;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
        ImageView imv = new ImageView(getClass().getResource("/Images/siguiente.png").toExternalForm());
        Button btn_add_cliente = new Button();
        btn_add_cliente.setOnAction(actionEvent -> new FormCliente(tbvClientes, null));
        btn_add_cliente.setGraphic(imv);
        tlbMenu.getItems().add(btn_add_cliente);

        tbvClientes = new TableView<>();
        tbvClientes = new TableView<ClienteModel>();
        CrearTable();

        vbox = new VBox(tlbMenu, tbvClientes);
        escena = new Scene(vbox, 500, 250);


    }

    private void CrearTable() {
        ClienteModel objCte = new ClienteModel();

        TableColumn<ClienteModel, String> tbcNomCte = new TableColumn<>("Cliente");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("Cliente"));

        TableColumn<ClienteModel, String> tbcTelCte = new TableColumn<>("Telefono");
        tbcTelCte.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        TableColumn<ClienteModel, String> tbcCorCte = new TableColumn<>("Correo");
        tbcCorCte.setCellValueFactory(new PropertyValueFactory<>("Correo"));

        TableColumn<ClienteModel, String> tbcEditar = new TableColumn<>("");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClienteModel, String>, TableCell<ClienteModel, String>>() {
            @Override
            public TableCell<ClienteModel, String> call(TableColumn<ClienteModel, String> clienteModelStringTableColumn) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn<ClienteModel, String> tbcEliminar = new TableColumn<>("");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClienteModel, String>, TableCell<ClienteModel, String>>() {
            @Override
            public TableCell<ClienteModel, String> call(TableColumn<ClienteModel, String> clienteModelStringTableColumn) {
                return new ButtonCell("Eliminar");
            }
        });

        tbvClientes.getColumns().addAll(tbcNomCte, tbcTelCte, tbcCorCte, tbcEditar,tbcEliminar);
        tbvClientes.setItems(objCte.Select());

    }
}
