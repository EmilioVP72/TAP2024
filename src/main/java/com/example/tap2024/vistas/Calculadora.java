package com.example.tap2024.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

   private Button[][] arrbtn;
   private TextField txtPantalla;
   private GridPane teclado;
   private VBox vbox;
   private Scene escena;
   private String[] strTeclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};

   private void CrearUI(){
       arrbtn = new Button[4][4];
       txtPantalla = new TextField("");
       txtPantalla.setAlignment(Pos.CENTER_RIGHT);
       txtPantalla.setEditable(false);
       teclado = new GridPane();
       CrearTeclado();
       vbox = new VBox(txtPantalla, teclado);
       escena = new Scene(vbox,300,350);

   }

   private void CrearTeclado(){
       for (int i = 0; i < arrbtn.length; i++) {
           for (int j = 0; j < arrbtn.length; j++) {
               arrbtn[j][i] = new Button(strTeclas[4*i + j]);
               arrbtn[j][i].setPrefSize(100,100);
               int finalI = i;
               int finalJ = j;
               arrbtn[j][i].setOnAction(event -> DetectarTecla(strTeclas[4* finalI + finalJ]));
               teclado.add(arrbtn[j][i],j,i);

           }

       }
   }

   public Calculadora(){
        CrearUI();
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
   }

   private void DetectarTecla(String tecla){
       //txtPantalla.setText("");
       txtPantalla.appendText(tecla);
   }
}
