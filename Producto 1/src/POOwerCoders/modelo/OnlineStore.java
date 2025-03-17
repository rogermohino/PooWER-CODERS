package POOwerCoders.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una tienda en línea que gestiona clientes, artículos y pedidos.
 */
public class OnlineStore {
    // Atributos
    private List<Cliente> clientes = new ArrayList<>();
    private List<Articulo> articulos = new ArrayList<>();
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
        return articulos;
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
        añadirElemento(articulos, a);
    }

    public void añadirCliente(Cliente c) {
        añadirElemento(clientes, c);
    }

    public void añadirPedido(Pedido p) {
        añadirElemento(pedidos, p);
    }
}
