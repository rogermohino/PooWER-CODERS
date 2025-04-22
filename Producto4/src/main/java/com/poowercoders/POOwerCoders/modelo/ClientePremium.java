package com.poowercoders.POOwerCoders.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Representa un cliente premium que hereda de la clase Cliente.
 * Los clientes premium tienen una cuota anual fija y un descuento en los gastos de envío.
 */
@Entity
@DiscriminatorValue("Premium") // Valor exacto del discriminador usado en la BBDD
public class ClientePremium extends Cliente {

    private static final double CUOTA_ANUAL = 30.0;
    private static final double DESCUENTO_ENVIO = 0.2;

    /** Constructor con parámetros */
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    /** Constructor vacío obligatorio para Hibernate */
    public ClientePremium() {
        super();
    }

    /** Obtiene la cuota anual del cliente premium */
    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    /** Obtiene el descuento en el envío para el cliente premium */
    public double getDescuentoEnvio() {
        return DESCUENTO_ENVIO;
    }

    @Override
    public String toString() {
        return "Cliente Premium {" +
                "Nombre:'" + getNombre() + '\'' +
                ", Domicilio:'" + getDomicilio() + '\'' +
                ", NIF:'" + getNif() + '\'' +
                ", Email:'" + getEmail() + '\'' +
                ", CuotaAnual: " + CUOTA_ANUAL +
                ", DescuentoEnvio: " + (DESCUENTO_ENVIO * 100) + "%" +
                '}';
    }
}
