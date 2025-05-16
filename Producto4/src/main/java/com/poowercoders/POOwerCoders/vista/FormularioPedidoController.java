package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlArticulo;
import com.poowercoders.POOwerCoders.controlador.ControlCliente;
import com.poowercoders.POOwerCoders.controlador.ControlPedido;
import com.poowercoders.POOwerCoders.modelo.Articulo;
import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.Pedido;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FormularioPedidoController {

    @FXML private TextField campoNumeroPedido;
    @FXML private TextField campoNifCliente;
    @FXML private TextField campoCodigoArticulo;
    @FXML private TextField campoCantidad;

    private final ControlPedido controlPedido = new ControlPedido();
    private final ControlCliente controlCliente = new ControlCliente();
    private final ControlArticulo controlArticulo = new ControlArticulo();

    @FXML
    private void guardarPedido() {
        try {
            int numeroPedido = Integer.parseInt(campoNumeroPedido.getText().trim());
            String nif = campoNifCliente.getText().trim();
            String codigo = campoCodigoArticulo.getText().trim();
            int cantidad = Integer.parseInt(campoCantidad.getText().trim());

            if (nif.isEmpty() || codigo.isEmpty()) {
                mostrarAlerta("NIF del cliente y código del artículo son obligatorios.");
                return;
            }

            Articulo articulo = controlArticulo.buscarArticulo(codigo);
            Cliente cliente = controlCliente.buscarCliente(nif);

            if (cliente == null) {
                mostrarAlerta("Cliente no encontrado con NIF: " + nif);
                return;
            }

            if (articulo == null) {
                mostrarAlerta("Artículo no encontrado con código: " + codigo);
                return;
            }

            Pedido pedido = new Pedido(numeroPedido, cliente, articulo, cantidad, LocalDateTime.now());
            controlPedido.agregarPedido(pedido);
            cerrarVentana();

        } catch (NumberFormatException e) {
            mostrarAlerta("❌ Número de pedido o cantidad no válidos.");
        } catch (DateTimeParseException e) {
            mostrarAlerta("❌ Fecha u hora no válidas.");
        } catch (Exception e) {
            mostrarAlerta("❌ Error al guardar el pedido: " + e.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) campoNumeroPedido.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
