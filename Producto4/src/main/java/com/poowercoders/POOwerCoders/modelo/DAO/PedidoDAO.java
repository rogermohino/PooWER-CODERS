package com.poowercoders.POOwerCoders.modelo.DAO;

import com.poowercoders.POOwerCoders.modelo.Pedido;
import java.util.List;

/**
 * Interfaz PedidoDAO que define las operaciones CRUD relacionadas con los pedidos.
 * Forma parte del patrón DAO para separar la lógica de persistencia de la lógica de negocio.
 */
public interface PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos.
     *
     * @param pedido Pedido a insertar.
     */
    void insertar(Pedido pedido);

    /**
     * Busca un pedido por su número identificador.
     *
     * @param numeroPedido Número del pedido a buscar.
     * @return Pedido correspondiente al número, o null si no se encuentra.
     */
    Pedido buscarPorNumero(int numeroPedido);

    /**
     * Lista todos los pedidos almacenados en la base de datos.
     *
     * @return Lista de todos los pedidos.
     */
    List<Pedido> listarTodos();

    /**
     * Elimina un pedido dado su número.
     *
     * @param numeroPedido Número del pedido a eliminar.
     */
    void eliminar(int numeroPedido);
}
