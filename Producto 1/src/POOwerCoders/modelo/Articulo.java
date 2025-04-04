package POOwerCoders.modelo;

/**
 * Clase Articulo que representa un producto de la tienda.
 * Contiene información como el código, descripción, precio de venta,
 * gastos de envío y el tiempo de preparación para el envío.
 */
public class Articulo {

    // Atributos privados del artículo
    private String codigo;              // Código único que identifica al artículo
    private String descripcion;         // Descripción textual del artículo
    private double precioVenta;         // Precio de venta del artículo
    private double gastosEnvio;         // Coste adicional por el envío del artículo
    private int tiempoPreparacion;      // Tiempo de preparación en minutos

    /**
     * Constructor de la clase Articulo.
     * Inicializa todos los atributos del artículo.
     *
     * @param codigo Código único del artículo.
     * @param descripcion Breve descripción del artículo.
     * @param precioVenta Precio base que se paga por el artículo.
     * @param gastosEnvio Coste adicional que se suma al precio total por el envío.
     * @param tiempoPreparacion Tiempo en minutos que tarda en prepararse el pedido.
     */
    public Articulo(String codigo, String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    // Getters y setters de todos los atributos

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

    /**
     * Devuelve una representación legible del objeto Articulo,
     * útil para mostrar información en pantalla o para depuración.
     *
     * @return Cadena con los detalles del artículo.
     */
    @Override
    public String toString() {
        return "Artículo Código: '" + codigo + '\'' +
                " {Descripción: '" + descripcion + '\'' +
                ", PrecioVenta: " + precioVenta +
                ", GastosEnvio: " + gastosEnvio +
                ", TiempoPreparacion: " + tiempoPreparacion + " min" +
                '}';
    }
}
