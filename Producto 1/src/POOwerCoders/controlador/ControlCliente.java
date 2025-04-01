package POOwerCoders.controlador;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.DAO.DAOFactory;

import java.util.List;

public class ControlCliente {

    private ClienteDAO clienteDAO;

    public ControlCliente() {
        this.clienteDAO = DAOFactory.getClienteDAO();
    }

    public void agregarCliente(Cliente cliente) {
        clienteDAO.insertar(cliente);
    }

    public void eliminarCliente(String nif) {
        clienteDAO.eliminar(nif);
    }

    public Cliente buscarCliente(String nif) {
        return clienteDAO.buscarPorNif(nif);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }
}

