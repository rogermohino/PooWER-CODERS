package com.poowercoders.POOwerCoders.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Representa un cliente de tipo estándar.
 * Hereda los atributos comunes de la clase base Cliente.
 */
@Entity
@DiscriminatorValue("Estandar")  // Este valor se usará en la columna discriminadora
public class ClienteEstandar extends Cliente {

    /**
     * Constructor para crear un cliente estándar con los datos básicos.
     *
     * @param nombre Nombre del cliente.
     * @param domicilio Domicilio del cliente.
     * @param nif Número de Identificación Fiscal.
     * @param email Correo electrónico.
     */
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    /** Constructor sin parámetros necesario para Hibernate */
    public ClienteEstandar() {
        super();
    }

    @Override
    public String toString() {
        return "Cliente Estándar {" +
                "Nombre:'" + getNombre() + '\'' +
                ", Domicilio:'" + getDomicilio() + '\'' +
                ", NIF:'" + getNif() + '\'' +
                ", Email:'" + getEmail() + '\'' +
                '}';
    }
}
