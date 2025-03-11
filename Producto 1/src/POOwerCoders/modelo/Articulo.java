package POOwerCoders.modelo;

/**
 * Representa un artículo con un código, descripción, precio de venta,
 * gastos de envío y tiempo de preparación.
 */
public class Articulo {
    //Atributos
    private String codigo;
    private String descripcion;
    private double precioVenta;
    private double gastosEnvio;
    private int tiempoPreparacion; //en milisegundos


    /**
     * Constructor de la clase POOwerCoders.modelo.Articulo.
     * 
     * @param codigo Código único del artículo.
     * @param descripcion Descripción del artículo.
     * @param precioVenta Precio de venta del artículo.
     * @param gastosEnvio Gastos de envío del artículo.
     * @param tiempoPreparacion Tiempo de preparación en milisegundos.
     */
    public Articulo(String codigo, String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }


    /**
     * Obtiene el código del artículo.
     * 
     * @return Código del artículo.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Establece el código del artículo.
     * 
     * @param codigo Nuevo código del artículo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene la descripción del artículo.
     * 
     * @return Descripción del artículo.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Establece la descripción del artículo.
     * 
     * @param descripcion Nueva descripción del artículo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtiene el precio de venta del artículo.
     * 
     * @return Precio de venta del artículo.
     */
    public double getPrecioVenta() {
        return precioVenta;
    }
    /**
     * Establece el precio de venta del artículo.
     * 
     * @param precioVenta Nuevo precio de venta del artículo.
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    /**
     * Obtiene los gastos de envío del artículo.
     * 
     * @return Gastos de envío del artículo.
     */
    public double getGastosEnvio() {
        return gastosEnvio;
    }
    /**
     * Establece los gastos de envío del artículo.
     * 
     * @param gastosEnvio Nuevos gastos de envío del artículo.
     */
    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }
    /**
     * Obtiene el tiempo de preparación del artículo.
     * 
     * @return Tiempo de preparación en milisegundos.
     */
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }
    /**
     * Establece el tiempo de preparación del artículo.
     * 
     * @param tiempoPreparacion Nuevo tiempo de preparación en milisegundos.
     */
    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }


    /**
     * Devuelve una representación en cadena del artículo.
     * 
     * @return Representación en cadena del artículo.
     */
    @Override
    public String toString() {
        return "POOwerCoders.modelo.Articulo{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", gastosEnvio=" + gastosEnvio +
                ", tiempoPreparacion=" + tiempoPreparacion +
                '}';
    }
}
