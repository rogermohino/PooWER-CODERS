package POOwerCoders.controlador;

// Importamos clases necesarias del modelo, excepciones y utilidades de fecha/hora
import POOwerCoders.modelo.Pedido;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.DAO.PedidoDAO;
import POOwerCoders.excepciones.DatosInvalidosException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ControlPedido - Se encarga de gestionar la lógica relacionada con los pedidos.
 * Pertenece a la capa de controlador en el patrón MVC.
 */
public class ControlPedido {

    // Atributo que representa el acceso a la base de datos para pedidos
    private PedidoDAO pedidoDAO;

    /**
     * Constructor por defecto.
     * Usa la fábrica DAO para obtener una instancia del DAO de pedidos.
     */
    public ControlPedido() {
        this.pedidoDAO = DAOFactory.getPedidoDAO();
    }

    /**
     * Agrega un pedido después de validar los datos.
     * @param pedido Objeto Pedido a agregar
     * @throws DatosInvalidosException si el pedido es inválido (cliente/artículo nulo, cantidad incorrecta o pedido duplicado)
     */
    public void agregarPedido(Pedido pedido) throws DatosInvalidosException {
        if (pedido.getCliente() == null || pedido.getArticulo() == null) {
            throw new DatosInvalidosException("Cliente o artículo no válido.");
        }
        if (pedido.getCantidad() <= 0) {
            throw new DatosInvalidosException("La cantidad debe ser mayor a cero.");
        }
        if (buscarPedido(pedido.getNumeroPedido()) != null) {
            throw new DatosInvalidosException("Ya existe un pedido con ese número.");
        }
        pedidoDAO.insertar(pedido); // Insertamos el pedido a través del DAO
    }

    /**
     * Busca un pedido por su número.
     * @param numero Número del pedido
     * @return Pedido encontrado o null si no existe
     */
    public Pedido buscarPedido(int numero) {
        return pedidoDAO.buscarPorNumero(numero);
    }

    /**
     * Devuelve todos los pedidos registrados en la base de datos.
     * @return Lista de pedidos
     */
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }

    /**
     * Elimina un pedido si todavía no ha sido enviado.
     * @param numeroPedido Número del pedido a eliminar
     * @throws DatosInvalidosException si no existe o ya ha sido enviado
     */
    public void eliminarPedido(int numeroPedido) throws DatosInvalidosException {
        Pedido pedido = pedidoDAO.buscarPorNumero(numeroPedido);
        if (pedido == null) {
            throw new DatosInvalidosException("No se encontró el pedido.");
        }

        // Comprobamos si ha pasado el tiempo de preparación
        long minutosTranscurridos = Duration.between(pedido.getFechaHora(), LocalDateTime.now()).toMinutes();
        if (minutosTranscurridos > pedido.getArticulo().getTiempoPreparacion()) {
            throw new DatosInvalidosException("No se puede eliminar: el pedido ya ha sido enviado.");
        }

        pedidoDAO.eliminar(numeroPedido); // Eliminamos el pedido
    }

    /**
     * Devuelve los pedidos pendientes de envío (aún no ha pasado el tiempo de preparación).
     * Puede filtrar por NIF del cliente.
     * @param nifCliente NIF del cliente o null si no se desea filtrar
     * @return Lista de pedidos pendientes
     * @throws DatosInvalidosException si no hay pedidos pendientes
     */
    public List<Pedido> obtenerPedidosPendientes(String nifCliente) throws DatosInvalidosException {
        List<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidoDAO.listarTodos()) {
            long minutos = Duration.between(p.getFechaHora(), LocalDateTime.now()).toMinutes();
            if (minutos < p.getArticulo().getTiempoPreparacion() &&
                    (nifCliente == null || p.getCliente().getNif().equalsIgnoreCase(nifCliente))) {
                pendientes.add(p);
            }
        }

        if (pendientes.isEmpty()) {
            throw new DatosInvalidosException("No hay pedidos pendientes.");
        }

        return pendientes;
    }

    /**
     * Devuelve los pedidos que ya han sido enviados (ha pasado el tiempo de preparación).
     * Puede filtrar por NIF del cliente.
     * @param nifCliente NIF del cliente o null si no se desea filtrar
     * @return Lista de pedidos enviados
     * @throws DatosInvalidosException si no hay pedidos enviados
     */
    public List<Pedido> obtenerPedidosEnviados(String nifCliente) throws DatosInvalidosException {
        List<Pedido> enviados = new ArrayList<>();
        for (Pedido p : pedidoDAO.listarTodos()) {
            long minutos = Duration.between(p.getFechaHora(), LocalDateTime.now()).toMinutes();
            if (minutos >= p.getArticulo().getTiempoPreparacion() &&
                    (nifCliente == null || p.getCliente().getNif().equalsIgnoreCase(nifCliente))) {
                enviados.add(p);
            }
        }

        if (enviados.isEmpty()) {
            throw new DatosInvalidosException("No hay pedidos enviados.");
        }

        return enviados;
    }
}
