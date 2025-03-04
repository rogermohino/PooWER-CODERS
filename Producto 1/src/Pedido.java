import java.time.LocalDateTime;

public class Pedido implements IPedido {
    //Atributos
    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;


    //Constructor
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }


    //Getters y Setters
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



    //toString
    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                '}';
    }


    //Métodos
    @Override
    public double calcularPrecio() {
        double precioTotal = (articulo.getPrecioVenta() * cantidad) + articulo.getGastosEnvio();
        if (cliente instanceof ClientePremium) {
            precioTotal -= articulo.getGastosEnvio() * ((ClientePremium) cliente).getDescuentoEnvio();
        }
        return precioTotal;
    }

    @Override
    public boolean cancelarPedido() {
        return LocalDateTime.now().isBefore(fechaHora.plusMinutes(articulo.getTiempoPreparacion()));
    }

}
