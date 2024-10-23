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

public class MenuCliente extends Stage {

    private Button btnComprarAlbum, btnComprarCancion;
    private Label lblTitulo;
    private VBox vboxPrincipal;
    private HBox hboxBotones;
    private Scene escena;

    public MenuCliente(String nombreCliente) {
        CrearUI(nombreCliente);
        this.setTitle("Interfaz de Cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(String nombreCliente) {
        Label lblTitulo = new Label("INTERFAZ DE CLIENTE");
        lblTitulo.setFont(new Font("Arial", 24));
        lblTitulo.setTextFill(Color.WHITE);

        Label lblBienvenido = new Label("BIENVENID@, " + nombreCliente);
        lblBienvenido.setFont(new Font("Arial", 18));
        lblBienvenido.setTextFill(Color.WHITE);

        ImageView imvAlbum = new ImageView(new Image(getClass().getResource("/imagenesSpotify/album.png").toExternalForm()));
        imvAlbum.setFitHeight(80);
        imvAlbum.setFitWidth(80);
        btnComprarAlbum = new Button("Comprar Álbum");
        btnComprarAlbum.setGraphic(imvAlbum);
        btnComprarAlbum.setStyle(
                "-fx-background-color: #6A5ACD;" +  // Color de fondo
                        "-fx-text-fill: white;" +            // Color del texto
                        "-fx-font-weight: bold;" +           // Negrita en el texto
                        "-fx-background-radius: 20;" +       // Borde redondeado
                        "-fx-padding: 10 20;"                // Espaciado interno
        );

        ImageView imvCancion = new ImageView(new Image(getClass().getResource("/imagenesSpotify/cancion.png").toExternalForm()));
        imvCancion.setFitHeight(80);
        imvCancion.setFitWidth(80);
        btnComprarCancion = new Button("Comprar Canción");
        btnComprarCancion.setGraphic(imvCancion);
        btnComprarCancion.setStyle(
                "-fx-background-color: #6A5ACD;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20;" +        // Borde redondeado
                        "-fx-padding: 10 20;"                 // Espaciado interno
        );

        hboxBotones = new HBox(20, btnComprarAlbum, btnComprarCancion);
        hboxBotones.setAlignment(Pos.CENTER);

        vboxPrincipal = new VBox(20, lblTitulo, lblBienvenido,hboxBotones);
        vboxPrincipal.setAlignment(Pos.CENTER);
        vboxPrincipal.setStyle(
                "-fx-padding: 20;" +
                        "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #5A36E8, #8A2BE2);" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 0);"
        );

        escena = new Scene(vboxPrincipal, 600, 300);
    }
}