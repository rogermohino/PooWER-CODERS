package POOwerCoders.modelo;

/**
 * Representa un cliente abstracto con atributos básicos como nombre, domicilio,
 * NIF y correo electrónico.
 */
public abstract class Cliente {
    // Atributos
    protected String nombre;
    protected String domicilio;
    protected String nif;
    protected String email;

    /**
     * Constructor de la clase POOwerCoders.modelo.Cliente.
     * 
     * @param nombre Nombre del cliente.
     * @param domicilio Domicilio del cliente.
     * @param nif Número de Identificación Fiscal del cliente.
     * @param email Correo electrónico del cliente.
     */
    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene el domicilio del cliente.
     * 
     * @return Domicilio del cliente.
     */
    public String getDomicilio() {
        return domicilio;
    }
    /**
     * Establece el domicilio del cliente.
     * 
     * @param domicilio Nuevo domicilio del cliente.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    /**
     * Obtiene el NIF del cliente.
     * 
     * @return NIF del cliente.
     */
    public String getNif() {
        return nif;
    }
    /**
     * Establece el NIF del cliente.
     * 
     * @param nif Nuevo NIF del cliente.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * Obtiene el correo electrónico del cliente.
     * 
     * @return Correo electrónico del cliente.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Establece el correo electrónico del cliente.
     * 
     * @param email Nuevo correo electrónico del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve una representación en cadena del cliente.
     * 
     * @return Representación en cadena del cliente.
     */
    @Override
    public String toString() {
        return String.format("Cliente {Nombre: '%s', Domicilio: '%s', NIF: '%s', Email: '%s'}", nombre, domicilio, nif, email);
    }
}

