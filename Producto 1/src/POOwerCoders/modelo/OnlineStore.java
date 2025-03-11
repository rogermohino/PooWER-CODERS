package POOwerCoders.modelo;

import java.util.ArrayList;
import java.util.List;
/**
 * Representa una tienda en línea que gestiona clientes, artículos y pedidos.
 */
public class OnlineStore {
    //Atributos
    private List<Cliente> clientes = new ArrayList<>();
    private List<Articulo> articulos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();


    /**
     * Obtiene la lista de clientes registrados.
     * 
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() { return clientes; }
    /**
     * Obtiene la lista de artículos disponibles en la tienda.
     * 
     * @return Lista de artículos.
     */
    public List<Articulo> getArticulos() { return articulos; }
    /**
     * Obtiene la lista de pedidos realizados en la tienda.
     * 
     * @return Lista de pedidos.
     */
    public List<Pedido> getPedidos() { return pedidos; }


    //Métodos utilizados en lugar de Setters, para evitar modificar toda la lista
    /**
     * Añade un artículo a la tienda.
     * 
     * @param a Artículo a añadir.
     */
    public void añadirArticulo(Articulo a) { articulos.add(a); }
    /**
     * Añade un cliente a la tienda.
     * 
     * @param c POOwerCoders.modelo.Cliente a añadir.
     */
    public void añadirCliente(Cliente c) { clientes.add(c); }
    /**
     * Añade un pedido a la tienda.
     * 
     * @param p POOwerCoders.modelo.Pedido a añadir.
     */
    public void añadirPedido(Pedido p) { pedidos.add(p); }
}

