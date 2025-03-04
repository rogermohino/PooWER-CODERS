public class ClienteEstandar extends Cliente {
    //Atributos


    //Constructores
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }


    //Getters y Setters


    //toString
    @Override
    public String toString() {
        return "ClienteEstandar{" +
                "nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
