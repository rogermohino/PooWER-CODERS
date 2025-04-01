package PO0werCoders.controlador;

import POOwerCoders.modelo.Cliente;
import PO0werCoders.modelo.dao.ClienteDAO;
import PO0werCoders.modelo.dao.DAOFactory;

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

