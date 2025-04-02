package POOwerCoders.controlador;

import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;


import java.util.ArrayList;
import java.util.List;

public class ControlCliente {

    private ClienteDAO clienteDAO;

    public ControlCliente() {
        this.clienteDAO = DAOFactory.getClienteDAO();
    }

    public void agregarCliente(Cliente cliente) throws DatosInvalidosException {
        if (cliente.getNombre().isBlank() || cliente.getNif().isBlank()) {
            throw new DatosInvalidosException("El nombre o NIF no pueden estar vacíos.");
        }
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

    public List<ClienteEstandar> obtenerClientesEstandar() {
        List<ClienteEstandar> estandar = new ArrayList<>();
        for (Cliente c : clienteDAO.listarTodos()) {
            if (c instanceof ClienteEstandar) {
                estandar.add((ClienteEstandar) c);
            }
        }
        return estandar;
    }

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




