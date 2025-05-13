package com.poowercoders.POOwerCoders.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa una tienda en línea que gestiona listas de clientes, artículos y pedidos.
 * Esta clase utiliza colecciones en memoria para realizar operaciones CRUD simples.
 *
 * Aunque en el proyecto usamos Hibernate para persistencia, esta clase puede servir
 * para pruebas o para simular operaciones antes de guardarlas en la base de datos.
 */
public class OnlineStore {

    // Lista de clientes registrados
    private List<Cliente> clientes = new ArrayList<>();

    // Mapa de artículos con el código como clave (clave única)
    private Map<String, Articulo> articulos = new HashMap<>();

    // Lista de pedidos realizados
    private List<Pedido> pedidos = new ArrayList<>();

    // ---------- MÉTODOS GENÉRICOS ----------

    /**
     * Añade un elemento de cualquier tipo a una lista.
     *
     * @param lista Lista a la que se quiere añadir el elemento.
     * @param elemento Elemento a añadir.
     * @param <T> Tipo del elemento.
     */
    public <T> void añadirElemento(List<T> lista, T elemento) {
        lista.add(elemento);
    }

    /**
     * Devuelve un elemento por índice desde cualquier lista.
     *
     * @param lista Lista desde la que se recupera.
     * @param indice Índice del elemento.
     * @param <T> Tipo del elemento.
     * @return Elemento en el índice dado.
     */
    public <T> T obtenerElemento(List<T> lista, int indice) {
        return lista.get(indice);
    }

    /**
     * Elimina un elemento de una lista si existe.
     *
     * @param lista Lista de donde se eliminará.
     * @param elemento Elemento a eliminar.
     * @param <T> Tipo del elemento.
     * @return true si se eliminó, false si no estaba en la lista.
     */
    public <T> boolean eliminarElemento(List<T> lista, T elemento) {
        return lista.remove(elemento);
    }

    // ---------- MÉTODOS DE ACCESO A CLIENTES, ARTÍCULOS Y PEDIDOS ----------

    /**
     * Devuelve todos los clientes registrados en la tienda.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Devuelve todos los artículos disponibles en la tienda.
     *
     * @return Lista de artículos.
     */
    public List<Articulo> getArticulos() {
        return new ArrayList<>(articulos.values()); // Convertimos el Map a List
    }

    /**
     * Devuelve todos los pedidos registrados en la tienda.
     *
     * @return Lista de pedidos.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // ---------- MÉTODOS ESPECÍFICOS ----------

    /**
     * Añade un nuevo artículo al catálogo de la tienda.
     * El código del artículo se usa como clave para evitar duplicados.
     *
     * @param a Artículo a registrar.
     */
    public void añadirArticulo(Articulo a) {
        articulos.put(a.getCodigo(), a);
    }

    /**
     * Devuelve un artículo del catálogo según su código.
     *
     * @param codigo Código del artículo.
     * @return Artículo correspondiente o null si no se encuentra.
     */
    public Articulo obtenerArticuloPorCodigo(String codigo) {
        return articulos.get(codigo);
    }

    /**
     * Añade un nuevo cliente a la tienda.
     *
     * @param c Cliente a registrar.
     */
    public void añadirCliente(Cliente c) {
        añadirElemento(clientes, c);
    }

    /**
     * Añade un nuevo pedido a la tienda.
     *
     * @param p Pedido a registrar.
     */
    public void añadirPedido(Pedido p) {
        añadirElemento(pedidos, p);
    }
}
