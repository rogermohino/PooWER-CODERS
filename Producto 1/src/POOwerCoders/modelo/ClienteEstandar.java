package POOwerCoders.modelo;

/**
 * Representa un cliente de tipo Estándar.
 * Hereda los atributos y métodos de la clase Cliente.
 *
 * Un cliente estándar no tiene ningún beneficio adicional (como descuentos).
 */
public class ClienteEstandar extends Cliente {

    // No se añaden atributos nuevos, ya que un cliente estándar no tiene propiedades adicionales

    /**
     * Constructor de la clase ClienteEstandar.
     * Llama al constructor de la clase base (Cliente) usando super().
     *
     * @param nombre Nombre del cliente.
     * @param domicilio Dirección del cliente.
     * @param nif Número de identificación fiscal (clave primaria).
     * @param email Correo electrónico del cliente.
     */
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);  // Llama al constructor de Cliente
    }

    /**
     * Sobrescribe el método toString() para mostrar los datos específicos
     * del cliente estándar en formato personalizado.
     *
     * @return Una cadena representando al cliente estándar.
     */
    @Override
    public String toString() {
        return "Cliente Estándar {" +
                "Nombre:'" + nombre + '\'' +
                ", Domicilio:'" + domicilio + '\'' +
                ", NIF:'" + nif + '\'' +
                ", Email:'" + email + '\'' +
                '}';
    }
}
