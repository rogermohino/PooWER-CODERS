public class ClientePremium extends Cliente {
    //Atributos
    private static final double CUOTA_ANUAL = 30.0;
    private static final double DESCUENTO_ENVIO = 0.2;

//hola
    //Constructor (sin incluir atributos propios, ya que todos los clientes premium tienen los mismos)
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    //Getters (sin setters ya que los atributos son constantes)
    public double getCuotaAnual() { return CUOTA_ANUAL; }
    public double getDescuentoEnvio() { return DESCUENTO_ENVIO; }


    //toString
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
