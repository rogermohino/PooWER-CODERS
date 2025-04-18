package POOwerCoders.modelo.DAO;


import POOwerCoders.modelo.Articulo;
import java.util.List;

public interface ArticuloDAO {
    void insertar(Articulo articulo);
    Articulo buscarPorCodigo(String codigo);
    List<Articulo> listarTodos();
    List<Articulo> buscarPorRangoPrecio(double min, double max);
    List<Articulo> buscarPorDescripcion(String palabraClave);

}
