package POOwerCoders.modelo.DAO;

// Importamos las clases necesarias del modelo
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;

import java.util.List;

/**
 * Interfaz ClienteDAO.
 * Define las operaciones necesarias para gestionar los datos de los clientes
 * en la base de datos. Implementaciones como ClienteDAOMySQL usarán JDBC para
 * dar funcionalidad real a estos métodos.
 */
public interface ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * La implementación debe diferenciar entre cliente estándar y premium.
     *
     * @param cliente El objeto Cliente que se desea guardar.
     */
    void insertar(Cliente cliente);

    /**
     * Elimina un cliente de la base de datos por su NIF.
     *
     * @param nif El NIF único del cliente a eliminar.
     */
    void eliminar(String nif);

    /**
     * Busca un cliente por su NIF.
     *
     * @param nif El NIF a buscar.
     * @return El cliente encontrado, o null si no existe.
     */
    Cliente buscarPorNif(String nif);

    /**
     * Devuelve una lista con todos los clientes (estándar y premium).
     *
     * @return Lista de clientes registrados en la base de datos.
     */
    List<Cliente> listarTodos();

    /**
     * Devuelve una lista de clientes estándar.
     *
     * @return Lista de objetos ClienteEstandar.
     */
    List<ClienteEstandar> listarClientesEstandar();

    /**
     * Devuelve una lista de clientes premium.
     *
     * @return Lista de objetos ClientePremium.
     */
    List<ClientePremium> listarClientesPremium();
}
