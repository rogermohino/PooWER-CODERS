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

    public List<Articulo> buscarPorRangoPrecio(double min, double max) {
        return articuloDAO.buscarPorRangoPrecio(min, max);
    }

    public List<Articulo> buscarPorDescripcion(String palabraClave) {
        return articuloDAO.buscarPorDescripcion(palabraClave);
    }
}

