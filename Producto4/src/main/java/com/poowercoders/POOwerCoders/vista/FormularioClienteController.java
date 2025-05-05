package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlCliente;
import com.poowercoders.POOwerCoders.excepciones.DatosInvalidosException;
import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.ClienteEstandar;
import com.poowercoders.POOwerCoders.modelo.ClientePremium;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FormularioClienteController implements Initializable {

    @FXML private TextField campoNif;
    @FXML private TextField campoNombre;
    @FXML private TextField campoDomicilio;
    @FXML private TextField campoEmail;
    @FXML private ComboBox<String> comboTipo;

    private final ControlCliente controlCliente = new ControlCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboTipo.getItems().addAll("Estandar", "Premium");
        comboTipo.setValue("Estandar"); // valor por defecto
    }

    @FXML
    private void guardarCliente() {
        String nif = campoNif.getText().trim();
        String nombre = campoNombre.getText().trim();
        String domicilio = campoDomicilio.getText().trim();
        String email = campoEmail.getText().trim();
        String tipo = comboTipo.getValue();

        if (nif.isEmpty() || nombre.isEmpty() || domicilio.isEmpty() || email.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente;
        if ("Premium".equalsIgnoreCase(tipo)) {
            cliente = new ClientePremium(nombre, domicilio, nif, email);
        } else {
            cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        }

        try {
            controlCliente.agregarCliente(cliente);
            cerrarVentana();
        } catch (DatosInvalidosException e) {
            mostrarAlerta("❌ " + e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("❌ Error al guardar cliente: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) campoNif.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
