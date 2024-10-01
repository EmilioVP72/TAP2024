package com.example.tap2024.vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Loteria extends Stage {

    private HBox hBoxmain, hboxbtns;
    private VBox vBoxtabla, vBoxbaraja;
    private Button btnanterior, btnsiguiente, btnstart;
    private Label lbltimer;
    private GridPane gptabla;
    private ImageView imvbaraja;
    private Scene escena;
    private String[] arrimagenes = {
            "1.jpeg", "2.jpeg", "3.jpeg", "4.jpeg", "5.jpeg", "6.jpeg", "7.jpeg", "8.jpeg", "9.jpeg",
            "10.jpeg", "11.jpeg", "12.jpeg", "13.jpeg", "14.jpeg", "15.jpeg", "16.jpeg", "17.jpeg",
            "18.jpeg", "19.jpeg", "20.jpeg", "21.jpeg", "22.jpeg", "23.jpeg", "24.jpeg", "25.jpeg",
            "26.jpeg", "27.jpeg", "28.jpeg", "29.jpeg", "30.jpeg", "31.jpeg", "32.jpeg", "33.jpeg",
            "34.jpeg", "35.jpeg", "36.jpeg", "37.jpeg", "38.jpeg", "39.jpeg", "40.jpeg", "41.jpeg",
            "42.jpeg", "43.jpeg", "44.jpeg", "45.jpeg", "46.jpeg", "47.jpeg", "48.jpeg", "49.jpeg",
            "50.jpeg", "51.jpeg", "52.jpeg", "53.jpeg", "54.jpeg"
    };
    private String[][] arrTablillas = new String[5][16];  // 5 tablillas, 16 imágenes cada una
    private int tablillaSeleccionada = 0;  // Índice para seleccionar la tablilla actual
    private int cartaActual = 0;  // Para rastrear la carta seleccionada
    private Timeline timeline;  // Temporizador para cambiar la carta automáticamente
    private Timeline timerActualizacion;  // Temporizador del reloj
    private Button[][] arbtntab;  // Botones en la tablilla
    private LocalTime tiempoTranscurrido; // Tiempo transcurrido en el juego
    private int tiempoRestante; // Representa segundos restantes para la próxima carta
    private int indiceCartaBaraja = 0;  // Índice para la baraja
    private int contadorCartasBloqueadas = 0; // Contador de cartas bloqueadas


    private Panel pnlmain;

    public Loteria() {
        CrearUI();
        this.setTitle("Loteria Troll :)");
        this.setScene(escena);
        this.show();
    }

    private void mezclarArreglo(String[] arreglo) {
        for (int i = arreglo.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = temp;
        }
    }

    private void CrearUI() {
        ImageView imvAnt, imvSig;
        imvAnt = new ImageView(new Image(getClass().getResource("/Images/anterior.png").toExternalForm()));
        imvAnt.setFitWidth(100);
        imvAnt.setFitHeight(100);
        imvSig = new ImageView(new Image(getClass().getResource("/Images/siguiente.png").toExternalForm()));
        imvSig.setFitHeight(100);
        imvSig.setFitWidth(100);

        gptabla = new GridPane();
        CrearTablilla();
        btnanterior = new Button();
        btnanterior.setGraphic(imvAnt);
        btnanterior.setOnAction(e -> cambiarTablilla(-1));
        btnsiguiente = new Button();
        btnsiguiente.setGraphic(imvSig);
        btnsiguiente.setOnAction(e -> cambiarTablilla(1));

        hboxbtns = new HBox(10, btnanterior, btnsiguiente);
        hboxbtns.getStyleClass().add("hbox-buttons");
        vBoxtabla = new VBox(10, gptabla, hboxbtns);

        CrearBaraja();
        hBoxmain = new HBox(20, vBoxtabla, vBoxbaraja);
        pnlmain = new Panel("Loteria Mexicana");
        pnlmain.getStyleClass().add("panel-success");
        pnlmain.setBody(hBoxmain);
        hBoxmain.setSpacing(20);
        hBoxmain.setPadding(new Insets(20));
        escena = new Scene(pnlmain, 900, 800);

        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena.getStylesheets().add(getClass().getResource("/Styles/Loteria.css").toExternalForm());
    }

    private void CrearBaraja() {
        lbltimer = new Label("00:00");
        lbltimer.setFont(new Font("Arial", 30));
        imvbaraja = new ImageView(new Image(getClass().getResource("/Images/dorso.jpeg").toExternalForm()));
        imvbaraja.setFitHeight(400);
        imvbaraja.setFitWidth(300);

        btnstart = new Button("INICIAR JUEGO");
        btnstart.getStyleClass().setAll("btn-sm", "btn-danger");
        btnstart.setOnAction(e -> iniciarJuego());

        lbltimer.setId("lbltimer");
        btnstart.setId("btnstart");
        vBoxbaraja = new VBox(20, lbltimer, imvbaraja, btnstart);
        vBoxbaraja.setSpacing(20);
        vBoxbaraja.getStyleClass().add("vbox-baraja");
    }

    private void CrearTablilla() {
        for (int t = 0; t < 5; t++) {
            mezclarArreglo(arrimagenes);
            for (int i = 0; i < 16; i++) {
                arrTablillas[t][i] = arrimagenes[i];
            }
        }

        arbtntab = new Button[4][4];
        actualizarTablilla();
    }

    private void cambiarTablilla(int direccion) {
        tablillaSeleccionada += direccion;
        if (tablillaSeleccionada < 0) {
            tablillaSeleccionada = 4;
        } else if (tablillaSeleccionada > 4) {
            tablillaSeleccionada = 0;
        }

        actualizarTablilla();
    }

    private void actualizarTablilla() {
        try {
            gptabla.getChildren().clear();
            int contador = 0;
            String[] tablillaActual = arrTablillas[tablillaSeleccionada];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (contador < tablillaActual.length) {
                        Image img = new Image(getClass().getResource("/Images/" + tablillaActual[contador]).toExternalForm());
                        ImageView imv = new ImageView(img);
                        imv.setFitWidth(100);
                        imv.setFitHeight(150);

                        Button btn = new Button();
                        btn.setGraphic(imv);
                        btn.setOnAction(e -> manejarClickBoton(btn, img));
                        arbtntab[i][j] = btn;
                        gptabla.add(btn, j, i);
                        contador++;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error actualizando la tablilla: " + ex.getMessage());
        }
    }

    private void manejarClickBoton(Button btn, Image imgBoton) {
        try {
            Image imgBaraja = imvbaraja.getImage();
            if (imgBoton.getUrl().equals(imgBaraja.getUrl())) {
                btn.setDisable(true);
                contadorCartasBloqueadas++; // Incrementar el contador
                verificarJuegoTerminado(); // Verificar si el juego ha terminado
            }
        } catch (Exception ex) {
            System.out.println("Error al manejar el clic del botón: " + ex.getMessage());
        }
    }

    private void mostrarMensajeLoteria() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Lotería!");
        alert.setHeaderText(null);
        alert.setContentText("¡Felicidades, has ganado la lotería!");
        alert.showAndWait();
        detenerBaraja();
    }

    private void mostrarMensajeIntentarloDeNuevo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Juego Terminado");
        alert.setHeaderText(null);
        alert.setContentText("No has bloqueado todas las imágenes. ¡Inténtalo de nuevo!");
        alert.showAndWait();
    }


    private void iniciarJuego() {
        if (timeline != null) {
            timeline.stop();
        }

        if (timerActualizacion != null) {
            timerActualizacion.stop();
        }

        try {
            btnstart.setDisable(true);
            btnanterior.setDisable(true);
            btnsiguiente.setDisable(true);

            mezclarArreglo(arrimagenes);
            indiceCartaBaraja = 0;
            actualizarBaraja();

            timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> avanzarCarta()));
            timeline.setCycleCount(arrimagenes.length);
            timeline.setOnFinished(e -> finalizarJuego());
            timeline.play();

            tiempoRestante = 5;
            actualizarReloj();

            timerActualizacion = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarReloj()));
            timerActualizacion.setCycleCount(Timeline.INDEFINITE);
            timerActualizacion.play();
        } catch (Exception ex) {
            System.out.println("Error al iniciar el juego: " + ex.getMessage());
        }
    }

    private void avanzarCarta() {
        try {
            indiceCartaBaraja++;
            if (indiceCartaBaraja >= arrimagenes.length) {
                finalizarJuego();
            } else {
                actualizarBaraja();
                tiempoRestante = 5;
            }
        } catch (Exception ex) {
            System.out.println("Error al avanzar la carta: " + ex.getMessage());
        }
    }

    private void actualizarBaraja() {
        try {
            Image imgBaraja = new Image(getClass().getResource("/Images/" + arrimagenes[indiceCartaBaraja]).toExternalForm());
            imvbaraja.setImage(imgBaraja);
        } catch (Exception ex) {
            System.out.println("Error al actualizar la baraja: " + ex.getMessage());
        }
    }

    private void actualizarReloj() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        } else {
            tiempoRestante = 5;
            avanzarCarta();
        }
        lbltimer.setText(String.format("00:%02d", tiempoRestante));
    }

    private void finalizarJuego() {
        if (timeline != null) {
            timeline.stop();
        }
        if (timerActualizacion != null) {
            timerActualizacion.stop();
        }

        // Aseguramos que el código se ejecute después de las animaciones
        Platform.runLater(() -> {
            if (contadorCartasBloqueadas == 16) {
                mostrarMensajeLoteria();  // Si se bloquearon las 16 cartas
            } else {
                mostrarMensajeIntentarloDeNuevo();  // Si no se bloquearon todas
            }
        });
    }

    private boolean verificarJuegoTerminado() {
        if (contadorCartasBloqueadas == 16) {
            Platform.runLater(() -> mostrarMensajeLoteria());
            detenerBaraja();
            return true;
        }
        return false;
    }


    private void detenerBaraja() {
        if (timeline != null) {
            timeline.stop();
        }

        if (timerActualizacion != null) {
            timerActualizacion.stop();
        }

        btnstart.setDisable(false);
        btnanterior.setDisable(false);
        btnsiguiente.setDisable(false);

        contadorCartasBloqueadas = 0; // Reiniciar contador
    }


}