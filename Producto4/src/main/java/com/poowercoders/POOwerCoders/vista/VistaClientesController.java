package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlCliente;
import com.poowercoders.POOwerCoders.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Parent;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VistaClientesController {

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colNif;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colTipo;
    @FXML private TextField campoBusqueda;
    @FXML private ComboBox<String> comboTipo;



    private final ControlCliente controlCliente = new ControlCliente();

    @FXML
    public void initialize() {
        colNif.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNif()));
        colNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        colEmail.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));
        colTipo.setCellValueFactory(cell -> {
            String tipo = cell.getValue().getClass().getSimpleName().replace("Cliente", "");
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });

        List<Cliente> clientes = controlCliente.listarClientes();
        ObservableList<Cliente> lista = FXCollections.observableArrayList(clientes);
        tablaClientes.setItems(lista);

        // HABILITAR SELECCIÓN
        tablaClientes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // INICIALIZAR COMBOBOX DE FILTRO
        comboTipo.getItems().addAll("Todos", "Estandar", "Premium");
        comboTipo.setValue("Todos"); // valor por defecto
    }


    @FXML
    private void cerrarVentana(ActionEvent event) {
        Window ventana = tablaClientes.getScene().getWindow();
        if (ventana instanceof Stage stage) {
            stage.close();
        }
    }

    @FXML
    private void abrirFormularioCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FormularioCliente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Añadir Cliente");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Actualizar tabla tras añadir cliente
            actualizarTabla();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void eliminarClienteSeleccionado() {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado == null) {
            mostrarAlerta("Selecciona un cliente para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Estás seguro de eliminar al cliente?");
        confirmacion.setContentText("NIF: " + clienteSeleccionado.getNif());

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                controlCliente.eliminarCliente(clienteSeleccionado.getNif());
                actualizarTabla();
            }
        });
    }


    @FXML
    private void buscarCliente() {
        String nif = campoBusqueda.getText().trim();

        if (nif.isEmpty()) {
            mostrarAlerta("Introduce un NIF para buscar.");
            return;
        }

        Cliente cliente = controlCliente.buscarCliente(nif);

        if (cliente == null) {
            mostrarAlerta("No se encontró ningún cliente con NIF: " + nif);
        } else {
            ObservableList<Cliente> lista = FXCollections.observableArrayList(cliente);
            tablaClientes.setItems(lista);
        }
    }

    @FXML
    private void filtrarPorTipo() {
        String tipoSeleccionado = comboTipo.getValue();

        if ("Todos".equals(tipoSeleccionado)) {
            actualizarTabla();
            return;
        }

        List<Cliente> filtrados = new ArrayList<>();
        for (Cliente c : controlCliente.listarClientes()) {
            String tipo = c.getClass().getSimpleName().replace("Cliente", "");
            if (tipo.equalsIgnoreCase(tipoSeleccionado)) {
                filtrados.add(c);
            }
        }

        ObservableList<Cliente> lista = FXCollections.observableArrayList(filtrados);
        tablaClientes.setItems(lista);
    }




    @FXML
    private void actualizarTabla() {
        List<Cliente> clientes = controlCliente.listarClientes();
        ObservableList<Cliente> lista = FXCollections.observableArrayList(clientes);
        tablaClientes.setItems(lista);
    }


    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
