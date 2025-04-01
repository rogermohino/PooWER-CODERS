package PO0werCoders.controlador;

import POOwerCoders.modelo.Pedido;
import PO0werCoders.modelo.dao.DAOFactory;
import PO0werCoders.modelo.dao.PedidoDAO;

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

