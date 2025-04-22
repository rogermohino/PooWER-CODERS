package com.poowercoders.POOwerCoders.modelo;

import jakarta.persistence.*;

/**
 * Representa un cliente genérico con los atributos comunes a todos los tipos de clientes.
 * Esta clase puede servir como clase base para herencia (por ejemplo: ClienteEstandar y ClientePremium).
 */
@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.JOINED) // permite herencia con tablas separadas
@DiscriminatorColumn(name = "tipoCliente")      // columna discriminadora en la base de datos
public class Cliente {

    // ==========================
    // Atributos del cliente
    // ==========================

    @Id
    @Column(name = "nif", nullable = false, length = 20)
    private String nif;

    @Column(name = "nombre", nullable = false, length = 100)
    protected String nombre;

    @Column(name = "domicilio", nullable = false, length = 200)
    protected String domicilio;

    @Column(name = "email", nullable = false, length = 100)
    protected String email;

    // ==========================
    // Constructores
    // ==========================

    public Cliente() {
        // Constructor por defecto requerido por JPA
    }

    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    // ==========================
    // Getters y Setters
    // ==========================

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDomicilio() { return domicilio; }

    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getNif() { return nif; }

    public void setNif(String nif) { this.nif = nif; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    // ==========================
    // toString
    // ==========================

    @Override
    public String toString() {
        return String.format("Cliente {Nombre: '%s', Domicilio: '%s', NIF: '%s', Email: '%s'}",
                nombre, domicilio, nif, email);
    }
}
