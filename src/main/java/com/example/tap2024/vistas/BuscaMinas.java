package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class BuscaMinas extends Stage {

    private Scene escena;
    private int size = 12;  // Tamaño de la cuadrícula (10x10 por defecto)
    private VBox vbox;
    private HBox hbox;
    private TextField tf;
    private Button[][] gridButtons;
    private GridPane grdpane;
    private boolean[][] BombasMatriz; // Matriz que almacenará la ubicación de las bombas
    private boolean[][] ReveleacionMatriz; // Matriz para verificar si la casilla ha sido revelada
    private boolean[][] BanderasMatriz; // Matriz para las banderas
    private int ContadorBombas = 0; // Número de bombas
    private Button restartbtn, startbtn; // Botón para reiniciar el juego

    public BuscaMinas() {
        grdpane = new GridPane();

        tf = new TextField();
        tf.setPromptText("Ingrese cantidad de bombas");

        startbtn = new Button("Crear BuscaMinas");
        restartbtn = new Button("Reiniciar Juego");
        restartbtn.setDisable(true);
        restartbtn.setOnAction(e -> reiniciarJuego(grdpane));

        hbox = new HBox(15, startbtn, restartbtn);

        startbtn.setOnAction(e -> {
            try {
                ContadorBombas = Integer.parseInt(tf.getText());
                if (ContadorBombas >= size * size) {
                    Alerta("Error", "La cantidad de bombas es mayor al tamaño de la cuadrícula");
                    return;
                }
                CrearUI(grdpane);
                restartbtn.setDisable(true);
            } catch (NumberFormatException ex) {
                Alerta("Error", "Por favor ingrese un número válido.");
            }
        });

        vbox = new VBox(tf, hbox, grdpane);

        escena = new Scene(vbox, 490, 450);
        this.setScene(escena);
        this.setTitle("BuscaMinas");
        this.show();
    }

    private void CrearUI(GridPane gridPane) {
        gridPane.getChildren().clear();
        gridButtons = new Button[size][size];
        BombasMatriz = new boolean[size][size];
        ReveleacionMatriz = new boolean[size][size];
        BanderasMatriz = new boolean[size][size];

        ColocarBombas(ContadorBombas);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button button = new Button();
                button.setMinSize(40, 40);
                final int r = row;
                final int c = col;

                button.setOnMouseClicked(event -> {
                    switch (event.getButton()) {
                        case PRIMARY:
                            Click_Izquierdo(r, c);
                            break;
                        case SECONDARY:
                            Click_Derecho(r, c);
                            break;
                    }
                });

                gridButtons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }
    }

    private void ColocarBombas(int bombs) {
        Random random = new Random();
        int placedBombs = 0;

        while (placedBombs < bombs) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!BombasMatriz[row][col]) {
                BombasMatriz[row][col] = true;
                placedBombs++;
            }
        }
    }

    private void Click_Izquierdo(int row, int col) {
        if (BanderasMatriz[row][col] || ReveleacionMatriz[row][col]) {
            return;
        }
        if (BombasMatriz[row][col]) {
            Alerta("Perdiste", "¡Has pisado una bomba!");
            Revelar_Bombas();
            restartbtn.setDisable(false);
            return;
        }
        RevelarCasilla(row, col);
        if (Validar_Victoria()) {
            Alerta("¡Ganaste!", "¡Has descubierto todas las casillas sin bombas!");
            restartbtn.setDisable(false);
        }
    }

    private void Click_Derecho(int row, int col) {
        if (ReveleacionMatriz[row][col]) {
            return;
        }
        if (!BanderasMatriz[row][col]) {
            gridButtons[row][col].setText("🚩");
            BanderasMatriz[row][col] = true;
        } else {
            gridButtons[row][col].setText("");
            BanderasMatriz[row][col] = false;
        }
    }

    private void RevelarCasilla(int row, int col) {
        if (ReveleacionMatriz[row][col]) return;

        int bombsAround = Contar_Bombas_Alrededor(row, col);
        ReveleacionMatriz[row][col] = true;

        if (bombsAround > 0) {
            gridButtons[row][col].setText(String.valueOf(bombsAround));
        } else {
            gridButtons[row][col].setStyle("-fx-background-color: #eaad6c;");
            gridButtons[row][col].setDisable(true);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (Posicion_Valida(newRow, newCol)) {
                        RevelarCasilla(newRow, newCol);
                    }
                }
            }
        }
    }

    private int Contar_Bombas_Alrededor(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (Posicion_Valida(newRow, newCol) && BombasMatriz[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void Revelar_Bombas() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (BombasMatriz[row][col]) {
                    gridButtons[row][col].setText("💣");
                    gridButtons[row][col].setStyle("-fx-background-color: red;");
                }
            }
        }
    }

    private boolean Validar_Victoria() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!BombasMatriz[row][col] && !ReveleacionMatriz[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean Posicion_Valida(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private void Alerta(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void reiniciarJuego(GridPane gridPane) {
        CrearUI(gridPane);
        restartbtn.setDisable(true);
    }
}
