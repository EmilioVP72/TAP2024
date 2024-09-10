package com.example.tap2024.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Loteria extends Stage{

    //INSTANCIAS
    private HBox hBoxmain, hboxbtns;
    private VBox vBoxtabla, vBoxbaraja;
    private Button btnanterior, btnsiguiente, btnstart;
    private Label lbltimer;
    private GridPane gptabla;
    private ImageView imvbaraja;
    private Scene escena;
    private String[] arrimagenes = {"barril.jpeg", "botella.jpeg", "catrin.jpeg", "chavorruco.jpeg", "concha.jpeg", "dorso.jpeg", "luchador.jpeg", "maceta.jpeg", "rosa.jpeg", "tacos.jpeg", "venado.jpeg"};
    private Button[][] arbtntab;

    //MANDAR A LLAMAR
    public Loteria() {
        CrearUI();
        this.setTitle("Loteria Troll :)");
        this.setScene(escena);
        this.show();
    }

    //PONER LOS MAS INTERNOS PRIMERO
    private void CrearUI() {
        ImageView imvAnt, imvSig;
        imvAnt = new ImageView(new Image(getClass().getResource("/images/anterior.png").toExternalForm()));
        imvAnt.setFitWidth(100);
        imvAnt.setFitHeight(100);
        imvSig = new ImageView(new Image(getClass().getResource("/images/siguiente.png").toExternalForm()));
        imvSig.setFitHeight(100);
        imvSig.setFitWidth(100);

        gptabla = new GridPane();
        CrearTablilla();
        btnanterior = new Button();
        btnanterior.setGraphic(imvAnt);
        btnsiguiente = new Button();
        btnsiguiente.setGraphic(imvSig);

        hboxbtns = new HBox(btnanterior, btnsiguiente);
        vBoxtabla = new VBox(gptabla, hboxbtns);

        CrearBaraja();
        hBoxmain= new HBox(vBoxtabla, vBoxbaraja);
        hBoxmain.setSpacing(20);
        hBoxmain.setPadding(new Insets(20));
        escena = new Scene(hBoxmain, 900, 800);

    }

    private void CrearBaraja(){
        //Image imgbaraja = new Image(getClass().getResource("/Images/dorso.jpeg").toExternalForm());;
        lbltimer = new Label("00.00");
        imvbaraja = new ImageView(getClass().getResource("/Images/dorso.jpeg").toExternalForm());
        imvbaraja.setFitHeight(400);
        imvbaraja.setFitWidth(300);
        btnstart = new Button("INICIAR JUEGO");
        vBoxbaraja = new VBox(lbltimer, imvbaraja, btnstart);
        vBoxbaraja.setSpacing(20);

    }


    private void CrearTablilla() {
        int contador = 0;

        for (int i = 0; i < 4; i++) {
            arbtntab = new Button[4][4];
            Image img;
            ImageView imv;
            for (int j = 0; j < 4; j++) {
                img = new Image(getClass().getResource("/Images/" + arrimagenes[contador]).toExternalForm());
                imv = new ImageView(img);
                imv.setFitWidth(100);
                imv.setFitHeight(150);
                arbtntab[j][i] = new Button();
                arbtntab[j][i].setGraphic(imv);
                gptabla.add(arbtntab[j][i], j, i);
                if (contador < arrimagenes.length - 1) {
                    contador++;
                }
            }

        }
    }


}
