package POOwerCoders.modelo.DAO;

// Importamos la clase Articulo y las utilidades para listas
import POOwerCoders.modelo.Articulo;
import java.util.List;

/**
 * Interfaz ArticuloDAO.
 * Define las operaciones que se deben implementar para acceder
 * a los datos de los artículos desde una fuente de datos (por ejemplo, una base de datos).
 *
 * Esta interfaz se implementará con JDBC en una clase como ArticuloDAOMySQL.
 */
public interface ArticuloDAO {

    /**
     * Inserta un nuevo artículo en la base de datos.
     * @param articulo El objeto Articulo que se quiere guardar.
     */
    void insertar(Articulo articulo);

    /**
     * Busca un artículo por su código identificador.
     * @param codigo El código único del artículo.
     * @return El objeto Articulo encontrado, o null si no existe.
     */
    Articulo buscarPorCodigo(String codigo);

    /**
     * Devuelve una lista con todos los artículos existentes en la base de datos.
     * @return Lista de artículos.
     */
    List<Articulo> listarTodos();

    /**
     * Busca los artículos cuyo precio esté entre un mínimo y un máximo especificados.
     * @param min Precio mínimo.
     * @param max Precio máximo.
     * @return Lista de artículos que cumplen con el rango.
     */
    List<Articulo> buscarPorRangoPrecio(double min, double max);

    /**
     * Busca los artículos cuya descripción contenga una palabra clave.
     * @param palabraClave Texto a buscar dentro de la descripción.
     * @return Lista de artículos que coinciden con la búsqueda.
     */
    List<Articulo> buscarPorDescripcion(String palabraClave);
}
