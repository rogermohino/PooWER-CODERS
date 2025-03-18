package POOwerCoders.modelo;

/**
 * Representa un cliente estándar que hereda de la clase POOwerCoders.modelo.Cliente.
 */
public class ClienteEstandar extends Cliente {
    //Atributos


    /**
     * Constructor de la clase POOwerCoders.modelo.ClienteEstandar.
     * 
     * @param nombre Nombre del cliente.
     * @param domicilio Domicilio del cliente.
     * @param nif Número de Identificación Fiscal del cliente.
     * @param email Correo electrónico del cliente.
     */
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }


    //Getters y Setters


    /**
     * Devuelve una representación en cadena del cliente estándar.
     * 
     * @return Representación en cadena del cliente estándar.
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
