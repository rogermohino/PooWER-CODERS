package POOwerCoders.modelo.DAO;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;

import java.util.List;

public interface ClienteDAO {
    void insertar(Cliente cliente);
    void eliminar(String nif);
    Cliente buscarPorNif(String nif);
    List<Cliente> listarTodos();
    List<ClienteEstandar> listarClientesEstandar();
    List<ClientePremium> listarClientesPremium();
}

