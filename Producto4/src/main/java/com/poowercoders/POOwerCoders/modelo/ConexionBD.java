package com.poowercoders.POOwerCoders.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar la conexión a la base de datos MySQL mediante JDBC.
 *
 * NOTA: Esta clase es útil en la implementación JDBC del proyecto.
 * Cuando se utilice Hibernate, se gestionará la conexión a través del archivo persistence.xml.
 */
public class ConexionBD {

    // ================================
    // Datos de conexión a la base de datos
    // ================================
    private static final String URL = "jdbc:mysql://localhost:3306/poowercoders";
    private static final String USER = "root";
    private static final String PASSWORD = "Chimmy.15";

    // Variable de conexión reutilizable
    private static Connection conexion = null;

    // ================================
    // Método para obtener la conexión (Singleton)
    // ================================
    public static Connection getConnection() {
        if (conexion == null) {
            try {
                System.out.println("🔄 Intentando conectar a la base de datos...");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión establecida con la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌ Error al conectar con la base de datos:");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    // ================================
    // Método para cerrar la conexión
    // ================================
    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
}
