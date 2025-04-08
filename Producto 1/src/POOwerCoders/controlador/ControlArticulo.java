package POOwerCoders.controlador;

// Importamos las clases necesarias
import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.DAO.ArticuloDAO;
import POOwerCoders.modelo.DAO.DAOFactory;

import java.sql.SQLException;
import java.util.List;



/**
 * Clase ControlArticulo - Forma parte de la capa de control dentro del patrón MVC.
 * Su función es gestionar las operaciones relacionadas con los artículos
 * conectándose con la base de datos a través del ArticuloDAO.
 */
public class ControlArticulo {

    // Atributo que representa el DAO que gestiona los datos de los artículos
    private ArticuloDAO articuloDAO;

    /**
     * Constructor por defecto.
     * Utiliza la clase DAOFactory para obtener una instancia concreta del DAO de artículos.
     */
    public ControlArticulo() {
        this.articuloDAO = DAOFactory.getArticuloDAO();
    }

    /**
     * Método para agregar un artículo a la base de datos.
     * @param articulo Objeto Articulo a insertar
     */
    public void agregarArticulo(Articulo articulo) {
        try {
            articuloDAO.insertar(articulo);
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar artículo: " + e.getMessage());
        }
    }

    /**
     * Busca un artículo en la base de datos por su código.
     * @param codigo Código del artículo
     * @return Articulo encontrado o null si no existe
     */
    public Articulo buscarArticulo(String codigo) {
        return articuloDAO.buscarPorCodigo(codigo);
    }

    /**
     * Devuelve una lista con todos los artículos registrados.
     * @return Lista de artículos
     */
    public List<Articulo> listarArticulos() {
        return articuloDAO.listarTodos();
    }

    /**
     * Busca artículos cuyo precio esté dentro del rango indicado.
     * @param min Precio mínimo
     * @param max Precio máximo
     * @return Lista de artículos en ese rango
     */
    public List<Articulo> buscarPorRangoPrecio(double min, double max) {
        return articuloDAO.buscarPorRangoPrecio(min, max);
    }

    /**
     * Busca artículos cuya descripción contenga una palabra clave.
     * @param palabraClave Texto a buscar en la descripción
     * @return Lista de artículos que contienen la palabra clave
     */
    public List<Articulo> buscarPorDescripcion(String palabraClave) {
        return articuloDAO.buscarPorDescripcion(palabraClave);
    }
}
