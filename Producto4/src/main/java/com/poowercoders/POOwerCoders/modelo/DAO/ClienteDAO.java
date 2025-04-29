package com.poowercoders.POOwerCoders.modelo.DAO;

import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.ClienteEstandar;
import com.poowercoders.POOwerCoders.modelo.ClientePremium;

import java.util.List;

/**
 * Interfaz DAO que define las operaciones de acceso a datos relacionadas con clientes.
 * Incluye métodos para insertar, eliminar, buscar y listar diferentes tipos de clientes.
 */
public interface ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente Cliente a insertar (puede ser estándar o premium).
     */
    void insertar(Cliente cliente);

    /**
     * Elimina un cliente de la base de datos mediante su NIF.
     * @param nif NIF del cliente a eliminar.
     */
    void eliminar(String nif);

    /**
     * Busca un cliente por su NIF.
     * @param nif NIF del cliente a buscar.
     * @return Cliente encontrado o null si no existe.
     */
    Cliente buscarPorNif(String nif);

    /**
     * Lista todos los clientes registrados.
     * @return Lista de todos los clientes (estándar y premium).
     */
    List<Cliente> listarTodos();

    /**
     * Lista únicamente los clientes estándar.
     * @return Lista de clientes de tipo ClienteEstandar.
     */
    List<ClienteEstandar> listarClientesEstandar();

    /**
     * Lista únicamente los clientes premium.
     * @return Lista de clientes de tipo ClientePremium.
     */
    List<ClientePremium> listarClientesPremium();

}
