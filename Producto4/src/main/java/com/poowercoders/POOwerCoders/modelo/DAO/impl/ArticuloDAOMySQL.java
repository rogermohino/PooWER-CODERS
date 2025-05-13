package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.Articulo;
import com.poowercoders.POOwerCoders.modelo.ConexionBD;
import com.poowercoders.POOwerCoders.modelo.DAO.ArticuloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ArticuloDAO utilizando JDBC y procedimientos almacenados.
 * Esta clase representa el DAO específico para MySQL y se encarga de la persistencia
 * de los datos relacionados con los artículos.
 */
public class ArticuloDAOMySQL implements ArticuloDAO {

    // Obtenemos la conexión a la base de datos desde la clase de utilidad ConexionBD
    private final Connection conexion = ConexionBD.getConnection();

    /**
     * Inserta un artículo en la base de datos utilizando un procedimiento almacenado.
     * La operación se realiza dentro de una transacción controlada manualmente.
     *
     * @param articulo Artículo a insertar.
     */
    @Override
    public void insertar(Articulo articulo) {
        String sql = "{CALL sp_insertar_articulo(?, ?, ?, ?, ?)}";

        try {
            conexion.setAutoCommit(false); // Inicia transacción manual

            try (CallableStatement stmt = conexion.prepareCall(sql)) {
                stmt.setString(1, articulo.getCodigo());
                stmt.setString(2, articulo.getDescripcion());
                stmt.setDouble(3, articulo.getPrecioVenta());
                stmt.setDouble(4, articulo.getGastosEnvio());
                stmt.setInt(5, articulo.getTiempoPreparacion());
                stmt.execute();
            }

            conexion.commit(); // Confirma la transacción
            System.out.println("✅ Artículo insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conexion.rollback(); // Revierte los cambios si ocurre un error
                System.err.println("⚠️ Error al insertar artículo. Se hizo rollback.");
            } catch (SQLException ex) {
                System.err.println("❌ Error al hacer rollback: " + ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                conexion.setAutoCommit(true); // Restaura el autocommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Recupera todos los artículos de la base de datos.
     *
     * @return Lista de artículos.
     */
    @Override
    public List<Articulo> listarTodos() {
        List<Articulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Articulo";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
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
            System.err.println("❌ Error al listar artículos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca un artículo por su código único.
     *
     * @param codigo Código del artículo.
     * @return Artículo encontrado o null si no existe.
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
            System.err.println("❌ Error al buscar artículo: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca artículos cuyo precio se encuentre en un rango determinado.
     *
     * @param min Precio mínimo.
     * @param max Precio máximo.
     * @return Lista de artículos en ese rango de precios.
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
            System.err.println("❌ Error al buscar artículos por rango de precio: " + e.getMessage());
        }

        return resultado;
    }

    /**
     * Busca artículos cuya descripción contenga una palabra clave.
     *
     * @param palabraClave Texto a buscar en la descripción.
     * @return Lista de artículos que coinciden con la descripción.
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
            System.err.println("❌ Error al buscar artículos por descripción: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public void eliminar(String codigo) {
        // Código SQL para eliminar el artículo
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM articulo WHERE codigo = ?")) {

            stmt.setString(1, codigo);
            int filas = stmt.executeUpdate();

            if (filas == 0) {
                System.out.println("⚠️ No se encontró ningún artículo con código: " + codigo);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar artículo: " + e.getMessage());
        }
    }

}
