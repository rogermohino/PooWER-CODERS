package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlPedido;
import com.poowercoders.POOwerCoders.modelo.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VistaPedidosController {

    @FXML private TableView<Pedido> tablaPedidos;
    @FXML private TableColumn<Pedido, Integer> colNumero;
    @FXML private TableColumn<Pedido, String> colCliente;
    @FXML private TableColumn<Pedido, String> colArticulo;
    @FXML private TableColumn<Pedido, Integer> colCantidad;
    @FXML private TableColumn<Pedido, String> colFecha;
    @FXML private TableColumn<Pedido, String> colPrecioTotal;
    @FXML private ComboBox<String> comboEstado;


    private final ControlPedido controlPedido = new ControlPedido();

    @FXML
    public void initialize() {
        colNumero.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getNumeroPedido()));
        colCliente.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCliente().getNombre()));
        colArticulo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getArticulo().getDescripcion()));
        colCantidad.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getCantidad()));
        colFecha.setCellValueFactory(cell -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return new javafx.beans.property.SimpleStringProperty(cell.getValue().getFechaHora().format(formatter));
        });
        colPrecioTotal.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(String.format("%.2f €", cell.getValue().calcularPrecio())));

        tablaPedidos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        actualizarTabla();
        comboEstado.getItems().addAll("Todos", "Pendientes", "Enviados");
        comboEstado.setValue("Todos");

    }

    @FXML
    private void abrirFormularioPedido() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FormularioPedido.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Pedido");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            actualizarTabla();
        } catch (IOException e) {
            mostrarAlerta("Error al cargar el formulario: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarPedidoSeleccionado() {
        Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();

        if (pedidoSeleccionado == null) {
            mostrarAlerta("Selecciona un pedido para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Estás seguro de eliminar el pedido?");
        confirmacion.setContentText("Pedido Nº: " + pedidoSeleccionado.getNumeroPedido());

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                try {
                    controlPedido.eliminarPedido(pedidoSeleccionado.getNumeroPedido());
                    actualizarTabla();
                } catch (Exception e) {
                    mostrarAlerta("❌ No se puede eliminar el pedido: " + e.getMessage());
                }
            }
        });
    }


    @FXML
    private void cerrarVentana() {
        Window ventana = tablaPedidos.getScene().getWindow();
        if (ventana instanceof Stage stage) {
            stage.close();
        }
    }

    @FXML
    private void actualizarTabla() {
        List<Pedido> pedidos = controlPedido.listarPedidos();
        ObservableList<Pedido> lista = FXCollections.observableArrayList(pedidos);
        tablaPedidos.setItems(lista);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private TextField campoBuscar; // Asegúrate de tener este fx:id en el FXML

    @FXML
    private void buscarPedido() {
        String texto = campoBuscar.getText().trim();
        if (texto.isEmpty()) {
            mostrarAlerta("Introduce un número de pedido.");
            return;
        }

        try {
            int numero = Integer.parseInt(texto);
            Pedido pedido = controlPedido.buscarPedido(numero);
            if (pedido != null) {
                ObservableList<Pedido> resultado = FXCollections.observableArrayList(pedido);
                tablaPedidos.setItems(resultado);
            } else {
                mostrarAlerta("No se encontró el pedido con número: " + numero);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El número de pedido debe ser un número entero.");
        }
    }

    @FXML
    private void filtrarPorEstado() {
        String estado = comboEstado.getValue();

        try {
            List<Pedido> pedidosFiltrados;
            if ("Pendientes".equalsIgnoreCase(estado)) {
                pedidosFiltrados = controlPedido.obtenerPedidosPendientes(null);
            } else if ("Enviados".equalsIgnoreCase(estado)) {
                pedidosFiltrados = controlPedido.obtenerPedidosEnviados(null);
            } else {
                pedidosFiltrados = controlPedido.listarPedidos();
            }

            tablaPedidos.setItems(FXCollections.observableArrayList(pedidosFiltrados));

        } catch (Exception e) {
            mostrarAlerta("⚠️ No se pudieron cargar los pedidos: " + e.getMessage());
        }
    }

}
