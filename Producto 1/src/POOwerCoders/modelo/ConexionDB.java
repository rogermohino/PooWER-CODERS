package POOwerCoders.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3307/poowercoders";
    private static final String USER = "root";
    private static final String PASSWORD = "151515Rog98**";

    private static Connection conexion = null;

    public static Connection getConnection() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión establecida con la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌ Error al conectar con la base de datos:");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion Cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexion:");
                e.printStackTrace();
            }
        }
    }
}
