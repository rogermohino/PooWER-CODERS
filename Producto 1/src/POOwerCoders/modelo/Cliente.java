package POOwerCoders.modelo;

/**
 * Clase base que representa un cliente con datos comunes como nombre,
 * domicilio, NIF y correo electrónico.
 * Esta clase se puede extender para crear tipos específicos de cliente (como ClienteEstandar o ClientePremium).
 */
public class Cliente {

    // Atributos protegidos para permitir que las subclases los hereden
    protected String nombre;        // Nombre completo del cliente
    protected String domicilio;     // Dirección del cliente
    protected String nif;           // Número de Identificación Fiscal
    protected String email;         // Correo electrónico

    /**
     * Constructor que inicializa un cliente con todos sus datos.
     *
     * @param nombre Nombre completo del cliente.
     * @param domicilio Dirección del cliente.
     * @param nif NIF del cliente (usado como identificador único).
     * @param email Correo electrónico del cliente.
     */
    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    // Métodos getter y setter para acceder y modificar los atributos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método toString sobrescrito que devuelve una representación textual
     * del objeto Cliente. Muy útil para depuración o mostrar en pantalla.
     *
     * @return Cadena con los datos del cliente.
     */
    @Override
    public String toString() {
        return String.format(
                "Cliente {Nombre: '%s', Domicilio: '%s', NIF: '%s', Email: '%s'}",
                nombre, domicilio, nif, email
        );
    }
}
