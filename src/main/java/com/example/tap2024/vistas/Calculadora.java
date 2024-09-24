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
    private Button btnClear;
    private GridPane teclado;
    private VBox vbox;
    private Scene escena;
    private String[] strTeclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};
    private double num1 = 0;
    private double num2 = 0;
    private double resultado;
    private boolean mostraresultado = false;
    private String operador = "";
    private boolean resultadoMostrado = false;

    private void CrearUI(){
        arrbtn = new Button[4][4];
        txtPantalla = new TextField("");
        txtPantalla.setAlignment(Pos.CENTER_RIGHT);
        txtPantalla.setEditable(false);
        teclado = new GridPane();
        CrearTeclado();
        btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {
            txtPantalla.clear();
            resetearProceso();
        });
        vbox = new VBox(txtPantalla, teclado, btnClear);
        btnClear.setId("font-button");
        escena = new Scene(vbox, 300, 350);
        escena.getStylesheets().add(getClass().getResource("/Styles/Calculadora.css").toExternalForm());

    }

    private void CrearTeclado(){
        for (int i = 0; i < arrbtn.length; i++) {
            for (int j = 0; j < arrbtn.length; j++) {
                arrbtn[j][i] = new Button(strTeclas[4 * i + j]);
                arrbtn[j][i].setPrefSize(100, 100);
                int finalI = i;
                int finalJ = j;
                arrbtn[j][i].setOnAction(event -> DetectarTecla(strTeclas[4 * finalI + finalJ]));
                teclado.add(arrbtn[j][i], j, i);
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
        if (esError()) {
            if (!tecla.matches("[0-9]") && !tecla.equals("Clear")) {
                return;
            }
            resetearProceso();
        }

        if (tecla.matches("[0-9.]")) {
            // Verificar si ya hay un punto en el número
            if (tecla.equals(".") && txtPantalla.getText().contains(".")) {
                txtPantalla.setText("Error: múltiples puntos");
                resultadoMostrado = true;
                return;
            }

            txtPantalla.appendText(tecla);

        } else if (tecla.matches("[+\\-*/]")) {
            if (!txtPantalla.getText().isEmpty()) {
                if (txtPantalla.getText().equals(".")) {
                    txtPantalla.setText("Error por punto");
                    resultadoMostrado = true;
                    return;
                }

                if (!operador.isEmpty()) {
                    num2 = Double.parseDouble(txtPantalla.getText());
                    num1 = realizarOperacion(num1, num2, operador);
                    txtPantalla.setText(String.valueOf(num1));
                } else {
                    num1 = Double.parseDouble(txtPantalla.getText());
                }
            }
            operador = tecla;
            txtPantalla.clear();
            resultadoMostrado = false;

        } else if (tecla.equals("=")) {
            if (!txtPantalla.getText().isEmpty()) {
                if (txtPantalla.getText().equals(".")) {
                    txtPantalla.setText("Error por punto");
                    mostraresultado = true;
                    return;
                }

                if (operador.equals("")) {
                    txtPantalla.setText(txtPantalla.getText());
                    resultadoMostrado = true;
                    return;
                }

                num2 = Double.parseDouble(txtPantalla.getText());

                if (operador.equals("/") && num2 == 0) {
                    txtPantalla.setText("Error: División por 0");
                    resultadoMostrado = true;
                    return;
                }

                num1 = realizarOperacion(num1, num2, operador);
                txtPantalla.setText(String.valueOf(num1));
                resultadoMostrado = true;

            } else {
                txtPantalla.setText("Error: null");
                resultadoMostrado = true;
                return;
            }

        } else if (tecla.equals("Clear")) {
            resetearProceso();
        }
    }

    private boolean esError() {
        return txtPantalla.getText().startsWith("Error");
    }

    private void resetearProceso() {
        txtPantalla.clear();
        num1 = 0;
        num2 = 0;
        operador = "";
        resultadoMostrado = false;
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
                return num1 / num2;
            default:
                return num1;
        }
    }
}
