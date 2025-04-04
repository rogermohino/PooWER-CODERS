package POOwerCoders.modelo.DAO.impl;

// Importamos las clases necesarias
import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.ConexionBD;
import POOwerCoders.modelo.DAO.ArticuloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ArticuloDAO utilizando JDBC y MySQL.
 * Gestiona todas las operaciones de base de datos relacionadas con la entidad Articulo.
 */
public class ArticuloDAOMySQL implements ArticuloDAO {

    // Obtenemos la conexión a la base de datos desde la clase ConexionBD
    private Connection conexion = POOwerCoders.modelo.ConexionBD.getConnection();

    /**
     * Inserta un nuevo artículo en la base de datos.
     * @param articulo El objeto Articulo que se va a insertar
     */
    @Override
    public void insertar(Articulo articulo) {
        String sql = "INSERT INTO Articulo (codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, articulo.getCodigo());
            stmt.setString(2, articulo.getDescripcion());
            stmt.setDouble(3, articulo.getPrecioVenta());
            stmt.setDouble(4, articulo.getGastosEnvio());
            stmt.setInt(5, articulo.getTiempoPreparacion());
            stmt.executeUpdate(); // Ejecuta el INSERT
        } catch (SQLException e) {
            System.err.println("Error al insertar artículo: " + e.getMessage());
        }
    }

    /**
     * Busca un artículo en la base de datos por su código.
     * @param codigo Código único del artículo
     * @return Articulo encontrado o null si no existe
     */
    @Override
    public Articulo buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM Articulo WHERE codigo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Articulo(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("gastosEnvio"),
                        rs.getInt("tiempoPreparacion")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar artículo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Recupera todos los artículos de la base de datos.
     * @return Lista de todos los artículos
     */
    @Override
    public List<Articulo> listarTodos() {
        List<Articulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Articulo";
        try (Statement stmt = conexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Articulo a = new Articulo(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("gastosEnvio"),
                        rs.getInt("tiempoPreparacion")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar artículos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca artículos cuyo precio de venta se encuentre dentro de un rango dado.
     * @param min Precio mínimo
     * @param max Precio máximo
     * @return Lista de artículos dentro del rango
     */
    @Override
    public List<Articulo> buscarPorRangoPrecio(double min, double max) {
        List<Articulo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM Articulo WHERE precioVenta BETWEEN ? AND ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo a = new Articulo(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("gastosEnvio"),
                        rs.getInt("tiempoPreparacion")
                );
                resultado.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar artículos por rango de precio: " + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca artículos que contengan una palabra clave en su descripción.
     * @param palabraClave Texto a buscar dentro de la descripción
     * @return Lista de artículos que coincidan con la palabra clave
     */
    @Override
    public List<Articulo> buscarPorDescripcion(String palabraClave) {
        List<Articulo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM Articulo WHERE descripcion LIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + palabraClave + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo a = new Articulo(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("gastosEnvio"),
                        rs.getInt("tiempoPreparacion")
                );
                resultado.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar artículos por descripción: " + e.getMessage());
        }
        return resultado;
    }
}
