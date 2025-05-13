package com.poowercoders.POOwerCoders.vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaPrincipalController {

    @FXML
    private void abrirClientes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaClientes.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Gestión de Clientes");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la ventana de clientes.");
        }
    }


    @FXML
    private void abrirArticulos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaArticulos.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Gestión de Artículos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la ventana de artículos.");
        }
    }

    @FXML
    private void abrirPedidos(ActionEvent event) {
        mostrarAlerta("Aquí se abrirá la gestión de pedidos.");
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Funcionalidad en desarrollo");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
