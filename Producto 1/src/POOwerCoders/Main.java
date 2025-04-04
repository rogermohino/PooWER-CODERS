package POOwerCoders;

// Importamos la vista principal de consola que contiene el menú de navegación
import POOwerCoders.vista.VistaConsola;

/**
 * Clase principal que contiene el método main.
 * Punto de entrada del programa. Desde aquí se lanza la aplicación.
 */
public class Main {

    /**
     * Método main: es el punto de inicio de cualquier aplicación Java.
     * Aquí se crea una instancia de VistaConsola e inicia la ejecución del programa.
     */
    public static void main(String[] args) {
        // Se crea una instancia de la vista principal del programa (VistaConsola)
        VistaConsola app = new VistaConsola();

        // Se llama al método que muestra el menú principal e inicia la interacción
        app.iniciarAplicacion();
    }
}
