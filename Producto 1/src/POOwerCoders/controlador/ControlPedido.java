package POOwerCoders.controlador;

import POOwerCoders.modelo.Pedido;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.DAO.PedidoDAO;

import java.util.List;

public class ControlPedido {

    private PedidoDAO pedidoDAO;

    public ControlPedido() {
        this.pedidoDAO = DAOFactory.getPedidoDAO();
    }

    public void agregarPedido(Pedido pedido) {
        pedidoDAO.insertar(pedido);
    }

    public Pedido buscarPedido(int numero) {
        return pedidoDAO.buscarPorNumero(numero);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }
}

