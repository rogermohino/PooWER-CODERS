package POOwerCoders.excepciones;

/**
 * Clase personalizada de excepción que representa errores por datos inválidos.
 * Extiende la clase Exception, lo que permite lanzar y capturar este tipo de errores
 * cuando los datos ingresados por el usuario no cumplen con ciertas validaciones.
 */
public class DatosInvalidosException extends Exception {

    /**
     * Constructor que recibe un mensaje personalizado de error.
     * Este mensaje será el que se muestre cuando se lance la excepción.
     *
     * @param mensaje Texto que describe el error
     */
    public DatosInvalidosException(String mensaje) {
        super(mensaje); // Llama al constructor de la clase base (Exception)
    }
}
