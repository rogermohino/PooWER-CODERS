package POOwerCoders.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa una tienda en línea que gestiona clientes, artículos y pedidos.
 * Esta clase contiene listas y mapas para almacenar todos los elementos del sistema.
 */
public class OnlineStore {

    // Lista de clientes registrados en la tienda.
    private List<Cliente> clientes = new ArrayList<>();

    // Mapa de artículos donde la clave es el código del artículo.
    private Map<String, Articulo> articulos = new HashMap<>();

    // Lista de pedidos realizados.
    private List<Pedido> pedidos = new ArrayList<>();

    // -------------------- MÉTODOS GENÉRICOS --------------------

    /**
     * Añade un elemento genérico a una lista.
     * Es un método reutilizable para cualquier tipo de lista.
     *
     * @param lista Lista a la que se añade el elemento.
     * @param elemento Elemento a añadir.
     * @param <T> Tipo del elemento.
     */
    public <T> void añadirElemento(List<T> lista, T elemento) {
        lista.add(elemento);
    }

    /**
     * Obtiene un elemento de una lista genérica por su índice.
     *
     * @param lista Lista de elementos.
     * @param indice Índice del elemento a obtener.
     * @param <T> Tipo del elemento.
     * @return Elemento en la posición especificada.
     */
    public <T> T obtenerElemento(List<T> lista, int indice) {
        return lista.get(indice);
    }

    /**
     * Elimina un elemento de una lista genérica.
     *
     * @param lista Lista de elementos.
     * @param elemento Elemento a eliminar.
     * @param <T> Tipo del elemento.
     * @return true si se eliminó correctamente, false si no estaba en la lista.
     */
    public <T> boolean eliminarElemento(List<T> lista, T elemento) {
        return lista.remove(elemento);
    }

    // -------------------- GETTERS --------------------

    /**
     * Obtiene todos los clientes registrados en la tienda.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Obtiene todos los artículos registrados en la tienda.
     *
     * @return Lista de artículos. Convierte el mapa de artículos en lista.
     */
    public List<Articulo> getArticulos() {
        return new ArrayList<>(articulos.values()); // Convertimos el Map en List
    }

    /**
     * Obtiene todos los pedidos realizados.
     *
     * @return Lista de pedidos.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // -------------------- MÉTODOS ESPECÍFICOS --------------------

    /**
     * Añade un artículo a la tienda.
     * Se guarda en el mapa usando su código como clave.
     *
     * @param a Artículo a añadir.
     */
    public void añadirArticulo(Articulo a) {
        articulos.put(a.getCodigo(), a);
    }

    /**
     * Busca un artículo por su código.
     *
     * @param codigo Código del artículo.
     * @return El artículo correspondiente, o null si no se encuentra.
     */
    public Articulo obtenerArticuloPorCodigo(String codigo) {
        return articulos.get(codigo);
    }

    /**
     * Añade un cliente a la tienda.
     *
     * @param c Cliente a añadir.
     */
    public void añadirCliente(Cliente c) {
        añadirElemento(clientes, c); // Usa el método genérico
    }

    /**
     * Añade un pedido a la tienda.
     *
     * @param p Pedido a añadir.
     */
    public void añadirPedido(Pedido p) {
        añadirElemento(pedidos, p); // Usa el método genérico
    }
}
