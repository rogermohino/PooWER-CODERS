package com.poowercoders.POOwerCoders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal para lanzar la interfaz gráfica con JavaFX.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga la vista principal (la crearemos más adelante)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/VistaPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Sistema de Gestión WoodShops");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

