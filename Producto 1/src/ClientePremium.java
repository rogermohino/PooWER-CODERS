/**
 * Representa un cliente premium que hereda de la clase Cliente.
 * Los clientes premium tienen una cuota anual fija y un descuento en el envío.
 */
public class ClientePremium extends Cliente {
    //Atributos
    private static final double CUOTA_ANUAL = 30.0;
    private static final double DESCUENTO_ENVIO = 0.2;

    /**
     * Constructor de la clase ClientePremium.
     * 
     * @param nombre Nombre del cliente.
     * @param domicilio Domicilio del cliente.
     * @param nif Número de Identificación Fiscal del cliente.
     * @param email Correo electrónico del cliente.
     */
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    //Getters (sin setters ya que los atributos son constantes)
    /**
     * Obtiene la cuota anual del cliente premium.
     * 
     * @return Cuota anual fija.
     */
    public double getCuotaAnual() { return CUOTA_ANUAL; }

    /**
     * Obtiene el descuento aplicado en los gastos de envío.
     * 
     * @return Descuento en envío.
     */
    public double getDescuentoEnvio() { return DESCUENTO_ENVIO; }


    /**
     * Devuelve una representación en cadena del cliente premium.
     * 
     * @return Representación en cadena del cliente premium.
     */
    @Override
    public String toString() {
        return "ClientePremium{" +
                "nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
