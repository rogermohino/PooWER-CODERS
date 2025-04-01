package PO0werCoders.modelo.dao;

import POOwerCoders.modelo.Articulo;
import java.util.List;

public interface ArticuloDAO {
    void insertar(Articulo articulo);
    Articulo buscarPorCodigo(String codigo);
    List<Articulo> listarTodos();
}
