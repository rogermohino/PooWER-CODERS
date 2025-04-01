package PO0werCoders.controlador;

import POOwerCoders.modelo.Articulo;
import PO0werCoders.modelo.dao.ArticuloDAO;
import PO0werCoders.modelo.dao.DAOFactory;

import java.util.List;

public class ControlArticulo {

    private ArticuloDAO articuloDAO;

    public ControlArticulo() {
        this.articuloDAO = DAOFactory.getArticuloDAO();
    }

    public void agregarArticulo(Articulo articulo) {
        articuloDAO.insertar(articulo);
    }

    public Articulo buscarArticulo(String codigo) {
        return articuloDAO.buscarPorCodigo(codigo);
    }

    public List<Articulo> listarArticulos() {
        return articuloDAO.listarTodos();
    }
}

