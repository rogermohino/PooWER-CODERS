package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.controlador.ControlArticulo;
import com.poowercoders.POOwerCoders.modelo.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VistaArticulosController {

    @FXML private TableView<Articulo> tablaArticulos;
    @FXML private TableColumn<Articulo, String> colCodigo;
    @FXML private TableColumn<Articulo, String> colDescripcion;
    @FXML private TableColumn<Articulo, Double> colPrecio;
    @FXML private TableColumn<Articulo, Double> colGastosEnvio;
    @FXML private TableColumn<Articulo, Integer> colTiempoPreparacion;
    @FXML private TextField campoBusqueda;
    @FXML private TextField campoPrecioMin;
    @FXML private TextField campoPrecioMax;


    private final ControlArticulo controlArticulo = new ControlArticulo();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCodigo()));
        colDescripcion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDescripcion()));
        colPrecio.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getPrecioVenta()));
        colGastosEnvio.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getGastosEnvio()));
        colTiempoPreparacion.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getTiempoPreparacion()));

        actualizarTabla();
    }

    @FXML
    private void abrirFormularioArticulo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FormularioArticulo.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Añadir Artículo");
            stage.setScene(scene);
            stage.showAndWait();
            actualizarTabla();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();  // 👈 Esto mostrará el error exacto en consola
            mostrarAlerta("❌ No se pudo abrir el formulario de artículo:\n" + e.getMessage());
        }
    }




    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void filtrarArticulos() {
        String palabraClave = campoBusqueda.getText().trim();
        if (palabraClave.isEmpty()) {
            mostrarAlerta("Introduce una palabra para buscar.");
            return;
        }

        List<Articulo> filtrados = controlArticulo.buscarPorDescripcion(palabraClave);
        tablaArticulos.setItems(FXCollections.observableArrayList(filtrados));
    }

    @FXML
    private void filtrarPorRangoPrecio() {
        try {
            double min = Double.parseDouble(campoPrecioMin.getText().trim());
            double max = Double.parseDouble(campoPrecioMax.getText().trim());

            List<Articulo> filtrados = controlArticulo.buscarPorRangoPrecio(min, max);
            tablaArticulos.setItems(FXCollections.observableArrayList(filtrados));
        } catch (NumberFormatException e) {
            mostrarAlerta("Introduce valores numéricos válidos para el precio.");
        }
    }



    @FXML
    private void eliminarArticuloSeleccionado() {
        Articulo seleccionado = tablaArticulos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Selecciona un artículo para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar Artículo");
        confirmacion.setHeaderText("¿Seguro que deseas eliminar el artículo?");
        confirmacion.setContentText("Código: " + seleccionado.getCodigo());

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                controlArticulo.eliminarArticulo(seleccionado.getCodigo());
                actualizarTabla();
                mostrarAlerta("⚠️ Falta implementar la lógica para eliminar el artículo.");
                actualizarTabla();
            }
        });
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) tablaArticulos.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void actualizarTabla() {
        List<Articulo> articulos = controlArticulo.listarArticulos();
        ObservableList<Articulo> lista = FXCollections.observableArrayList(articulos);
        tablaArticulos.setItems(lista);
    }


}
