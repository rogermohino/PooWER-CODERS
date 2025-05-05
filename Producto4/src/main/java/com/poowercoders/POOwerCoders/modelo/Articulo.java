package com.poowercoders.POOwerCoders.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un artículo que puede ser vendido en la tienda.
 * Contiene información como código, descripción, precio, gastos de envío y tiempo de preparación.
 */
@Entity
@Table(name = "articulo") // nombre de la tabla en la base de datos
public class Articulo {

    // Atributos privados del artículo

    @Id
    private String codigo;            // Código único que identifica al artículo
    private String descripcion;       // Breve descripción del artículo
    private double precioVenta;       // Precio de venta al cliente
    private double gastosEnvio;       // Coste adicional por envío
    private int tiempoPreparacion;    // Tiempo necesario para preparar el pedido (en minutos)

    // Constructor por defecto requerido por Hibernate
    public Articulo() {}

    /**
     * Constructor que permite crear un objeto Articulo con todos sus atributos.
     */
    public Articulo(String codigo, String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public String toString() {
        return "Artículo Código:'" + codigo + '\'' +
                " {Descripción:'" + descripcion + '\'' +
                ", PrecioVenta:" + precioVenta +
                ", GastosEnvio:" + gastosEnvio +
                ", TiempoPreparacion:" + tiempoPreparacion +
                '}';
    }
}
