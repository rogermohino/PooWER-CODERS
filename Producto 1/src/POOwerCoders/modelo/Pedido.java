package POOwerCoders.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un pedido realizado por un cliente en la tienda en línea.
 * Implementa la interfaz IPedido, lo que obliga a implementar los métodos calcularPrecio y cancelarPedido.
 */
public class Pedido implements IPedido {

    // ------------------- Atributos -------------------

    private int numeroPedido;              // Número único que identifica el pedido
    private Cliente cliente;               // Cliente que realiza el pedido
    private Articulo articulo;            // Artículo que se está comprando
    private int cantidad;                 // Cantidad del artículo
    private LocalDateTime fechaHora;      // Fecha y hora en que se realiza el pedido

    // ------------------- Constructor -------------------

    /**
     * Constructor para crear un nuevo pedido con todos sus datos.
     */
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    // ------------------- Getters y Setters -------------------

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

    // ------------------- Métodos de la Interfaz IPedido -------------------

    /**
     * Calcula el precio total del pedido:
     * - Multiplica el precio del artículo por la cantidad pedida.
     * - Suma los gastos de envío.
     * - Si el cliente es premium, aplica descuento en los gastos de envío.
     */
    @Override
    public double calcularPrecio() {
        double precioTotal = (articulo.getPrecioVenta() * cantidad) + articulo.getGastosEnvio();

        // Si el cliente es premium, se aplica el descuento en el envío
        if (cliente instanceof ClientePremium) {
            precioTotal -= articulo.getGastosEnvio() * ((ClientePremium) cliente).getDescuentoEnvio();
        }

        return precioTotal;
    }

    /**
     * Verifica si el pedido puede ser cancelado.
     * Un pedido solo puede cancelarse si aún no ha pasado el tiempo de preparación del artículo.
     */
    @Override
    public boolean cancelarPedido() {
        return LocalDateTime.now().isBefore(
                fechaHora.plusMinutes(articulo.getTiempoPreparacion())
        );
    }

    // ------------------- Representación en texto -------------------

    /**
     * Devuelve los datos del pedido en formato legible, con la fecha formateada.
     */
    @Override
    public String toString() {
        // Formatea la fecha para mostrarla de forma clara
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = fechaHora.format(formatter);

        return "Número de Pedido: " + numeroPedido +
                " {Cliente: " + cliente +
                ", Artículo: " + articulo +
                ", Cantidad: " + cantidad +
                ", Fecha y Hora: " + fechaFormateada +
                ", Precio Total: " + calcularPrecio() + "€" +
                '}';
    }
}
