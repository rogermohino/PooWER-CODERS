package com.poowercoders.POOwerCoders.modelo.DAO;

import com.poowercoders.POOwerCoders.modelo.Articulo;

import java.util.List;

/**
 * Interfaz DAO para definir operaciones de acceso a datos relacionadas con la entidad Articulo.
 * Esta interfaz permite desacoplar la lógica de persistencia del resto de la aplicación.
 */
public interface ArticuloDAO {

    /**
     * Inserta un nuevo artículo en el sistema de persistencia.
     * @param articulo El artículo a insertar.
     */
    void insertar(Articulo articulo);

    /**
     * Busca un artículo a partir de su código.
     * @param codigo Código único del artículo.
     * @return El artículo correspondiente o null si no se encuentra.
     */
    Articulo buscarPorCodigo(String codigo);

    /**
     * Lista todos los artículos almacenados.
     * @return Lista de todos los artículos.
     */
    List<Articulo> listarTodos();

    /**
     * Busca artículos cuyo precio esté dentro del rango dado.
     * @param min Precio mínimo.
     * @param max Precio máximo.
     * @return Lista de artículos que cumplen el criterio.
     */
    List<Articulo> buscarPorRangoPrecio(double min, double max);

    /**
     * Busca artículos que contengan una palabra clave en su descripción.
     * @param palabraClave Palabra clave a buscar.
     * @return Lista de artículos que contienen la palabra clave.
     */
    List<Articulo> buscarPorDescripcion(String palabraClave);
}
