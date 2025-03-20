package POOwerCoders.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa una tienda en línea que gestiona clientes, artículos y pedidos.
 */
public class OnlineStore {
    // Atributos
    private List<Cliente> clientes = new ArrayList<>();
    private Map<String, Articulo> articulos = new HashMap<>();
    private List<Pedido> pedidos = new ArrayList<>();

    /**
     * Métodos genéricos para manipular elementos en cualquiera de las listas.
     */
    public <T> void añadirElemento(List<T> lista, T elemento) {
        lista.add(elemento);
    }

    public <T> T obtenerElemento(List<T> lista, int indice) {
        return lista.get(indice);
    }

    public <T> boolean eliminarElemento(List<T> lista, T elemento) {
        return lista.remove(elemento);
    }

    /**
     * Obtiene la lista de clientes registrados.
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Obtiene la lista de artículos disponibles en la tienda.
     * @return Lista de artículos.
     */
    public List<Articulo> getArticulos() {
        return new ArrayList<>(articulos.values()); // Convierte el HashMap en una List
    }

    /**
     * Obtiene la lista de pedidos realizados en la tienda.
     * @return Lista de pedidos.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // Métodos específicos (ahora usan los métodos genéricos internamente)
    public void añadirArticulo(Articulo a) {
        articulos.put(a.getCodigo(), a);
    }

    public Articulo obtenerArticuloPorCodigo(String codigo) {
        return articulos.get(codigo);
    }

    public void añadirCliente(Cliente c) {
        añadirElemento(clientes, c);
    }

    public void añadirPedido(Pedido p) {
        añadirElemento(pedidos, p);
    }
}
