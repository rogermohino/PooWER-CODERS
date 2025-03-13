package POOwerCoders.modelo;

import java.time.LocalDateTime;
/**
 * Representa un pedido realizado por un cliente en la tienda en línea.
 * Implementa la interfaz POOwerCoders.modelo.IPedido.
 */
public class Pedido implements IPedido {
    //Atributos
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;


    /**
     * Constructor de la clase POOwerCoders.modelo.Pedido.
     * 
     * @param numeroPedido Número de identificación del pedido.
     * @param cliente POOwerCoders.modelo.Cliente que realiza el pedido.
     * @param articulo Artículo solicitado.
     * @param cantidad Cantidad del artículo solicitado.
     * @param fechaHora Fecha y hora del pedido.
     */
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }


    /**
     * Obtiene el número del pedido.
     * 
     * @return Número del pedido.
     */
    public int getNumeroPedido() {
        return numeroPedido;
    }
    /**
     * Establece el número del pedido.
     * 
     * @param numeroPedido Nuevo número de pedido.
     */
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    /**
     * Obtiene el cliente que realizó el pedido.
     * 
     * @return POOwerCoders.modelo.Cliente del pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Establece el cliente del pedido.
     * 
     * @param cliente Nuevo cliente del pedido.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtiene el artículo del pedido.
     * 
     * @return Artículo del pedido.
     */
    public Articulo getArticulo() {
        return articulo;
    }
    /**
     * Establece el artículo del pedido.
     * 
     * @param articulo Nuevo artículo del pedido.
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    /**
     * Obtiene la cantidad de artículos del pedido.
     * 
     * @return Cantidad de artículos.
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Establece la cantidad de artículos del pedido.
     * 
     * @param cantidad Nueva cantidad de artículos.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Obtiene la fecha y hora en que se realizó el pedido.
     * 
     * @return Fecha y hora del pedido.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    /**
     * Establece la fecha y hora del pedido.
     * 
     * @param fechaHora Nueva fecha y hora del pedido.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }


    /**
     * Calcula el precio total del pedido, incluyendo el precio del artículo y los gastos de envío.
     * Si el cliente es premium, se aplica un descuento en los gastos de envío.
     * 
     * @return Precio total del pedido.
     */
    @Override
    public double calcularPrecio() {
        double precioTotal = (articulo.getPrecioVenta() * cantidad) + articulo.getGastosEnvio();
        if (cliente instanceof ClientePremium) {
            precioTotal -= articulo.getGastosEnvio() * ((ClientePremium) cliente).getDescuentoEnvio();
        }
        return precioTotal;
    }
    /**
     * Determina si el pedido puede ser cancelado.
     * Un pedido solo puede cancelarse si aún está dentro del tiempo de preparación.
     * 
     * @return true si el pedido puede cancelarse, false en caso contrario.
     */
    @Override
    public boolean cancelarPedido() {
        return LocalDateTime.now().isBefore(fechaHora.plusMinutes(articulo.getTiempoPreparacion()));
    }

    /**
     * Devuelve una representación en cadena del pedido.
     * 
     * @return Representación en cadena del pedido.
     */
    @Override
    public String toString() {
        return "NuúmeroPedido:" + numeroPedido +
                " {Cliente:" + cliente +
                ", Artículo:" + articulo +
                ", Cantidad: " + cantidad +
                ", FechaHora:" + fechaHora +
                '}';
    }

}
