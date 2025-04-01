package POOwerCoders.controlador;

import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.DAO.ArticuloDAO;
import POOwerCoders.modelo.DAO.DAOFactory;

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

