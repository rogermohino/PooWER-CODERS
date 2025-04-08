package POOwerCoders.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para manejar la conexión con la base de datos MySQL.
 * Implementa el patrón Singleton: mantiene una única conexión reutilizable durante la ejecución del programa.
 */
public class ConexionBD {

    // URL de conexión a la base de datos. Incluye el nombre de la BBDD "poowercoders".
    private static final String URL = "jdbc:mysql://localhost:3307/poowercoders";

    // Usuario y contraseña para acceder a la base de datos.
    private static final String USER = "root";
    private static final String PASSWORD = "151515Rog98**";

    // Objeto que almacenará la conexión activa.
    private static Connection conexion = null;

    /**
     * Método estático que devuelve una conexión activa a la base de datos.
     * Si la conexión no existe todavía, se crea.
     *
     * @return Objeto Connection ya conectado.
     */
    public static Connection getConnection() {
        if (conexion == null) { // Si no hay conexión, la creamos
            try {
                System.out.println("🔄 Intentando conectar a la base de datos...");
                // Se crea la conexión con los datos proporcionados
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión establecida con la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌ Error al conectar con la base de datos:");
                e.printStackTrace(); // Muestra detalles del error
            }
        }
        return conexion;
    }

    /**
     * Método para cerrar la conexión a la base de datos.
     * Se usa al terminar el programa o cuando ya no se necesita la conexión.
     */
    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close(); // Se cierra la conexión
                System.out.println("Conexion Cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexion:");
                e.printStackTrace(); // Muestra detalles del error
            }
        }
    }
}
