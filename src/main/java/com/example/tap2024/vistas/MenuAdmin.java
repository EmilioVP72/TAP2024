package com.example.tap2024.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuAdmin extends Stage {

    private Label lblCliente, lblCancion, lblArtista, lblAlbum, lblInterpretacion, lblistaCancion, lblVenta, lblventa_deta;
    private Button btnCliente, btnCancion, btnArtista, btnAlbum, btnInterpretacion, btnListaCancion, btnVenta, btnventa_deta;
    private VBox vboxPrincipal, vboxClientes, vboxCancion, vboxArtista, vboxAlbum, vboxInterpretacion, vboxListaCancion, vboxVenta, vboxventa_deta;
    private HBox hboxSecundario1, hboxSecundario2;
    private Scene escena;

    public MenuAdmin(String nombreUsuario) {
        CrearUI(nombreUsuario);
        this.setTitle("Ventana Administrador :)");
        this.setScene(escena);
        this.show();
    }


    private void CrearUI(String nombreUsuario) {

        Label lblTitulo = new Label("INTERFAZ DE ADMINISTRADOR");
        lblTitulo.setFont(new Font("Arial", 24));
        lblTitulo.setTextFill(Color.WHITE);

        Label lblBienvenido = new Label("BIENVENID@, " + nombreUsuario);
        lblBienvenido.setFont(new Font("Arial", 18));
        lblBienvenido.setTextFill(Color.WHITE);

        VBox vboxTitulo = new VBox(lblTitulo, lblBienvenido);
        vboxTitulo.setAlignment(Pos.CENTER);
        vboxTitulo.setSpacing(10);

        ImageView imvCliente = new ImageView(new Image(getClass().getResource("/imagenesSpotify/clientes.png").toExternalForm()));
        imvCliente.setFitHeight(60);
        imvCliente.setFitWidth(60);

        ImageView imvCancion = new ImageView(new Image(getClass().getResource("/imagenesSpotify/cancion.png").toExternalForm()));
        imvCancion.setFitHeight(60);
        imvCancion.setFitWidth(60);

        ImageView imvArtista = new ImageView(new Image(getClass().getResource("/imagenesSpotify/artista.png").toExternalForm()));
        imvArtista.setFitHeight(60);
        imvArtista.setFitWidth(60);

        ImageView imvAlbum = new ImageView(new Image(getClass().getResource("/imagenesSpotify/album.png").toExternalForm()));
        imvAlbum.setFitHeight(60);
        imvAlbum.setFitWidth(60);

        ImageView imvInterpretacion = new ImageView(new Image(getClass().getResource("/imagenesSpotify/interpretacion.png").toExternalForm()));
        imvInterpretacion.setFitHeight(60);
        imvInterpretacion.setFitWidth(60);

        ImageView imvListaCancion = new ImageView(new Image(getClass().getResource("/imagenesSpotify/listacanciones.png").toExternalForm()));
        imvListaCancion.setFitHeight(60);
        imvListaCancion.setFitWidth(60);

        ImageView imvVenta = new ImageView(new Image(getClass().getResource("/imagenesSpotify/venta.png").toExternalForm()));
        imvVenta.setFitHeight(60);
        imvVenta.setFitWidth(60);

        ImageView imvVenta_deta = new ImageView(new Image(getClass().getResource("/imagenesSpotify/detalle.png").toExternalForm()));
        imvVenta_deta.setFitHeight(60);
        imvVenta_deta.setFitWidth(60);

        lblAlbum = new Label("Album");
        btnAlbum = new Button();
        btnAlbum.setGraphic(imvAlbum);
        btnAlbum.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblArtista = new Label("Artista");
        btnArtista = new Button();
        btnArtista.setGraphic(imvArtista);
        btnArtista.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblCancion = new Label("Cancion");
        btnCancion = new Button();
        btnCancion.setGraphic(imvCancion);
        btnCancion.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblCliente = new Label("Clientes");
        btnCliente = new Button();
        btnCliente.setGraphic(imvCliente);
        btnCliente.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );
        btnCliente.setOnAction(actionEvent -> new ListaClientes());

        vboxClientes = new VBox(lblCliente, btnCliente);
        vboxClientes.setAlignment(Pos.CENTER);
        vboxClientes.setSpacing(10);

        vboxCancion = new VBox(lblCancion, btnCancion);
        vboxCancion.setAlignment(Pos.CENTER);
        vboxCancion.setSpacing(10);

        vboxArtista = new VBox(lblArtista, btnArtista);
        vboxArtista.setAlignment(Pos.CENTER);
        vboxArtista.setSpacing(10);

        vboxAlbum = new VBox(lblAlbum, btnAlbum);
        vboxAlbum.setAlignment(Pos.CENTER);
        vboxAlbum.setSpacing(10);

        hboxSecundario1 = new HBox(vboxClientes, vboxCancion, vboxArtista, vboxAlbum);
        hboxSecundario1.setSpacing(20);
        hboxSecundario1.setAlignment(Pos.CENTER);

        lblInterpretacion = new Label("Interpretación");
        btnInterpretacion = new Button();
        btnInterpretacion.setGraphic(imvInterpretacion);
        btnInterpretacion.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblistaCancion = new Label("Lista de Canciones");
        btnListaCancion = new Button();
        btnListaCancion.setGraphic(imvListaCancion);
        btnListaCancion.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblVenta = new Label("Venta");
        btnVenta = new Button();
        btnVenta.setGraphic(imvVenta);
        btnVenta.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        lblventa_deta = new Label("Venta Detallada");
        btnventa_deta = new Button();
        btnventa_deta.setGraphic(imvVenta_deta);
        btnventa_deta.setStyle(
                "-fx-background-color: #4b6cb7;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        vboxInterpretacion = new VBox(lblInterpretacion, btnInterpretacion);
        vboxInterpretacion.setAlignment(Pos.CENTER);
        vboxInterpretacion.setSpacing(10);

        vboxListaCancion = new VBox(lblistaCancion, btnListaCancion);
        vboxListaCancion.setAlignment(Pos.CENTER);
        vboxListaCancion.setSpacing(10);

        vboxVenta = new VBox(lblVenta, btnVenta);
        vboxVenta.setAlignment(Pos.CENTER);
        vboxVenta.setSpacing(10);

        vboxventa_deta = new VBox(lblventa_deta, btnventa_deta);
        vboxventa_deta.setAlignment(Pos.CENTER);
        vboxventa_deta.setSpacing(10);

        hboxSecundario2 = new HBox(vboxInterpretacion, vboxListaCancion, vboxVenta, vboxventa_deta);
        hboxSecundario2.setSpacing(20);
        hboxSecundario2.setAlignment(Pos.CENTER);

        vboxPrincipal = new VBox(vboxTitulo, hboxSecundario1, hboxSecundario2);
        vboxPrincipal.setSpacing(30); // Separación entre las HBoxes
        vboxPrincipal.setAlignment(Pos.CENTER);
        vboxPrincipal.setStyle("-fx-background-color: linear-gradient(to right, #4b6cb7, #182848);"); // Gama de colores azul y morada

        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;";
        lblAlbum.setStyle(labelStyle);
        lblArtista.setStyle(labelStyle);
        lblCancion.setStyle(labelStyle);
        lblCliente.setStyle(labelStyle);
        lblInterpretacion.setStyle(labelStyle);
        lblistaCancion.setStyle(labelStyle);
        lblVenta.setStyle(labelStyle);
        lblventa_deta.setStyle(labelStyle);

        escena = new Scene(vboxPrincipal, 600, 600);
    }
}
