package POOwerCoders.modelo.DAO;

import POOwerCoders.modelo.Pedido;
import java.util.List;

public interface PedidoDAO {
    void insertar(Pedido pedido);
    Pedido buscarPorNumero(int numeroPedido);
    List<Pedido> listarTodos();
    void eliminar(int numeroPedido);
}

