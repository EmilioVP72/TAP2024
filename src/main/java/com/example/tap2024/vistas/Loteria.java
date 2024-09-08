package com.example.tap2024.vistas;

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
    private ImageView imgbaraja;
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
        imvSig = new ImageView(new Image(getClass().getResource("/images/siguiente.png").toExternalForm()));

        gptabla = new GridPane();
        CrearTablilla();
        btnanterior = new Button();
        btnanterior.setGraphic(imvAnt);
        btnsiguiente = new Button();
        btnsiguiente.setGraphic(imvSig);

        hboxbtns = new HBox(btnanterior, btnsiguiente);
        vBoxtabla = new VBox(gptabla, hboxbtns);

        hBoxmain= new HBox(vBoxtabla);
        escena = new Scene(hBoxmain, 700, 700);



    }

    private void CrearTablilla() {

        for (int i = 0; i < 4; i++) {
            arbtntab = new Button[4][4];
            Image img;
            ImageView imv;
            for (int j = 0; j < 4; j++) {
                img =new Image(getClass().getResource("/images/barril.jpeg").toExternalForm());
                imv = new ImageView(img);
                imv.setFitWidth(100);
                imv.setFitHeight(150);
                arbtntab[j][i] = new Button();
                arbtntab[j][i].setGraphic(imv);
                gptabla.add(arbtntab[j][i], j, i);


            }

        }
    }

}
