package POOwerCoders.modelo.DAO;

// Importamos la clase Pedido y las utilidades para listas
import POOwerCoders.modelo.Pedido;
import java.util.List;

/**
 * Interfaz PedidoDAO.
 * Define las operaciones básicas que se pueden realizar con los pedidos
 * en la base de datos. Esta interfaz será implementada por una clase concreta
 * como PedidoDAOMySQL usando JDBC.
 */
public interface PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido El objeto Pedido que se desea almacenar.
     */
    void insertar(Pedido pedido);

    /**
     * Busca un pedido por su número identificador único.
     *
     * @param numeroPedido El número de pedido a buscar.
     * @return El pedido encontrado, o null si no existe.
     */
    Pedido buscarPorNumero(int numeroPedido);

    /**
     * Devuelve una lista con todos los pedidos registrados en la base de datos.
     *
     * @return Lista de objetos Pedido.
     */
    List<Pedido> listarTodos();

    /**
     * Elimina un pedido de la base de datos a partir de su número identificador.
     *
     * @param numeroPedido El número del pedido que se desea eliminar.
     */
    void eliminar(int numeroPedido);
}
