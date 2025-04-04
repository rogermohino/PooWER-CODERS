package POOwerCoders.modelo;

/**
 * Representa un cliente premium que hereda de la clase Cliente.
 * Los clientes premium tienen características especiales:
 * - Pagan una cuota anual.
 * - Obtienen un descuento fijo en los gastos de envío.
 */
public class ClientePremium extends Cliente {

    // Atributos constantes: no cambian para ningún cliente premium.
    private static final double CUOTA_ANUAL = 30.0;         // Cuota anual fija
    private static final double DESCUENTO_ENVIO = 0.2;      // 20% de descuento en envío

    /**
     * Constructor que inicializa los atributos heredados desde la clase Cliente.
     *
     * @param nombre     Nombre del cliente.
     * @param domicilio  Dirección del cliente.
     * @param nif        NIF del cliente (identificador).
     * @param email      Email del cliente.
     */
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);  // Llama al constructor de la superclase Cliente
    }

    // --- Getters (no hay setters porque los valores son constantes) ---

    /**
     * Devuelve la cuota anual que paga el cliente premium.
     *
     * @return Cuota anual fija (30.0 euros).
     */
    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    /**
     * Devuelve el porcentaje de descuento aplicado en los gastos de envío.
     *
     * @return Descuento como valor decimal (0.2 equivale a 20%).
     */
    public double getDescuentoEnvio() {
        return DESCUENTO_ENVIO;
    }

    /**
     * Representación del cliente premium en forma de texto.
     * Útil para mostrar los datos en pantalla.
     *
     * @return Cadena con los datos principales del cliente.
     */
    @Override
    public String toString() {
        return "Cliente Premium {" +
                "Nombre:'" + nombre + '\'' +
                ", Domicilio:'" + domicilio + '\'' +
                ", NIF'" + nif + '\'' +
                ", Email:'" + email + '\'' +
                '}';
    }
}
