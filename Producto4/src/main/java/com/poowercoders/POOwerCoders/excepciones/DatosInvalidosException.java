package com.poowercoders.POOwerCoders.excepciones;

/**
 * Excepción personalizada para representar errores relacionados con datos inválidos
 * en operaciones de negocio.
 */
public class DatosInvalidosException extends Exception {

    /**
     * Constructor que permite establecer el mensaje de error.
     *
     * @param mensaje Mensaje descriptivo del error ocurrido.
     */
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}
