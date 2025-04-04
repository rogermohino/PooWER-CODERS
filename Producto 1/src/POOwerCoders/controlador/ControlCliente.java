package POOwerCoders.controlador;

// Importamos las clases necesarias
import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ControlCliente - Se encarga de la lógica de negocio relacionada con los clientes.
 * Pertenece a la capa de controlador en el patrón MVC.
 */
public class ControlCliente {

    // Atributo que representa el DAO de clientes para interactuar con la base de datos
    private ClienteDAO clienteDAO;

    /**
     * Constructor por defecto.
     * Obtiene una instancia del ClienteDAO desde la DAOFactory.
     */
    public ControlCliente() {
        this.clienteDAO = DAOFactory.getClienteDAO();
    }

    /**
     * Añade un cliente a la base de datos tras validar que el nombre y NIF no estén vacíos.
     * @param cliente Objeto Cliente a agregar
     * @throws DatosInvalidosException si el nombre o NIF están vacíos
     */
    public void agregarCliente(Cliente cliente) throws DatosInvalidosException {
        if (cliente.getNombre().isBlank() || cliente.getNif().isBlank()) {
            throw new DatosInvalidosException("El nombre o NIF no pueden estar vacíos.");
        }
        clienteDAO.insertar(cliente);
    }

    /**
     * Elimina un cliente de la base de datos por su NIF.
     * @param nif Identificador del cliente
     */
    public void eliminarCliente(String nif) {
        clienteDAO.eliminar(nif);
    }

    /**
     * Busca un cliente en la base de datos por su NIF.
     * @param nif NIF del cliente
     * @return Cliente encontrado o null si no existe
     */
    public Cliente buscarCliente(String nif) {
        return clienteDAO.buscarPorNif(nif);
    }

    /**
     * Lista todos los clientes registrados en la base de datos.
     * @return Lista de clientes
     */
    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    /**
     * Devuelve una lista con todos los clientes de tipo estándar.
     * Filtra los resultados a partir de todos los clientes almacenados.
     * @return Lista de ClienteEstandar
     */
    public List<ClienteEstandar> obtenerClientesEstandar() {
        List<ClienteEstandar> estandar = new ArrayList<>();
        for (Cliente c : clienteDAO.listarTodos()) {
            if (c instanceof ClienteEstandar) {
                estandar.add((ClienteEstandar) c);
            }
        }
        return estandar;
    }

    /**
     * Devuelve una lista con todos los clientes de tipo premium.
     * Filtra los resultados a partir de todos los clientes almacenados.
     * @return Lista de ClientePremium
     */
    public List<ClientePremium> obtenerClientesPremium() {
        List<ClientePremium> premium = new ArrayList<>();
        for (Cliente c : clienteDAO.listarTodos()) {
            if (c instanceof ClientePremium) {
                premium.add((ClientePremium) c);
            }
        }
        return premium;
    }
}
