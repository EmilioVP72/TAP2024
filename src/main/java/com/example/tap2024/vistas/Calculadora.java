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
   private  Button btnClear;
   private GridPane teclado;
   private VBox vbox;
   private Scene escena;
   private String[] strTeclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};
   private double num1;
   private double num2;
   private double resultado;
   private String operador = "";
   private Button clear;

   private void CrearUI(){
       arrbtn = new Button[4][4];
       txtPantalla = new TextField("");
       txtPantalla.setAlignment(Pos.CENTER_RIGHT);
       txtPantalla.setEditable(false);
       teclado = new GridPane();
       CrearTeclado();
       btnClear = new Button("Clear");
       btnClear.setOnAction(e -> txtPantalla.clear());
       vbox = new VBox(txtPantalla, teclado, btnClear);
       btnClear.setId("font-button");
       escena = new Scene(vbox,300,350);
       escena.getStylesheets().add(getClass().getResource("/Styles/Calculadora.css").toExternalForm());

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

    private void DetectarTecla(String tecla) {
        if (tecla.matches("[0-9.]")) {
            if ((!txtPantalla.getText().isEmpty()) && (tecla.isEmpty())) {
                txtPantalla.appendText(tecla);

            }else {
                ;
                txtPantalla.appendText(tecla);
            }

        } else if (tecla.matches("[+\\-*/]")) {
            if (!txtPantalla.getText().isEmpty()) {
                // Si ya hay un valor en pantalla, se guarda como num1 si no hay operador previo
                if (operador.isEmpty()) {
                    num1 = Double.parseDouble(txtPantalla.getText());
                } else {
                    // Si ya hay un operador, calculamos el resultado parcial
                    num2 = Double.parseDouble(txtPantalla.getText());
                    num1 = realizarOperacion(num1, num2, operador);
                }
            }
            operador = tecla;
            txtPantalla.clear();

        } else if (tecla.equals("=")) {
            if (!txtPantalla.getText().isEmpty()) {
                num2 = Double.parseDouble(txtPantalla.getText());
                num1 = realizarOperacion(num1, num2, operador);
                txtPantalla.setText(String.valueOf(num1));
                operador = "";

            }

        } else if (tecla.equals("Clear")) {
            txtPantalla.clear();
            num1 = 0;
            num2 = 0;
            operador = "";
        }
    }

    private double realizarOperacion(double num1, double num2, String operador) {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":

                if (num2 == 0) {
                    return num1 / num2;
                } else {

                    txtPantalla.setText("infinity");
                }
            default:
                return num1;
        }
    }

}
