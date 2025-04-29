package com.poowercoders.POOwerCoders.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pedidos")
public class Pedido implements IPedido {

    @Id
    @Column(name = "numeroPedido")
    private int numeroPedido;

    @ManyToOne
    @JoinColumn(name = "nifCliente", referencedColumnName = "nif")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigoArticulo", referencedColumnName = "codigo")
    private Articulo articulo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    public Pedido() {
    }

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public double calcularPrecio() {
        double precioBase = articulo.getPrecioVenta() * cantidad;
        double gastosEnvio = articulo.getGastosEnvio();
        if (cliente instanceof ClientePremium premium) {
            gastosEnvio -= gastosEnvio * premium.getDescuentoEnvio();
        }
        return precioBase + gastosEnvio;
    }

    @Override
    public boolean cancelarPedido() {
        return LocalDateTime.now().isBefore(fechaHora.plusMinutes(articulo.getTiempoPreparacion()));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = fechaHora.format(formatter);

        return "Número de Pedido: " + numeroPedido +
                " {Cliente: " + cliente +
                ", Artículo: " + articulo +
                ", Cantidad: " + cantidad +
                ", Fecha y Hora: " + fechaFormateada +
                ", Precio Total: " + String.format("%.2f", calcularPrecio()) + "€" +
                " }";
    }
}
