package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlArticulo;
import com.poowercoders.POOwerCoders.modelo.Articulo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioArticuloController {

    @FXML private TextField campoCodigo;
    @FXML private TextField campoDescripcion;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoEnvio;
    @FXML private TextField campoPreparacion;

    private final ControlArticulo controlArticulo = new ControlArticulo();

    @FXML
    private void guardarArticulo() {
        try {
            String codigo = campoCodigo.getText().trim();
            String descripcion = campoDescripcion.getText().trim();
            double precio = Double.parseDouble(campoPrecio.getText().trim());
            double envio = Double.parseDouble(campoEnvio.getText().trim());
            int tiempo = Integer.parseInt(campoPreparacion.getText().trim());

            if (codigo.isEmpty() || descripcion.isEmpty()) {
                mostrarAlerta("Código y descripción son obligatorios.");
                return;
            }

            Articulo articulo = new Articulo(codigo, descripcion, precio, envio, tiempo);
            controlArticulo.agregarArticulo(articulo);
            cerrarVentana();

        } catch (NumberFormatException e) {
            mostrarAlerta("❌ Precio, envío o preparación no válidos.");
        } catch (Exception e) {
            mostrarAlerta("❌ Error al guardar el artículo: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) campoCodigo.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
