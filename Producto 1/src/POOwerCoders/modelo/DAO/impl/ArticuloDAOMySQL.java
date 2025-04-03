package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.ConexionBD;
import POOwerCoders.modelo.DAO.ArticuloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAOMySQL implements ArticuloDAO {

    private Connection conexion = POOwerCoders.modelo.ConexionBD.getConnection();

    @Override
    public void insertar(Articulo articulo) {
        String sql = "{CALL sp_insertar_articulo(?, ?, ?, ?, ?)}";

        try {
            conexion.setAutoCommit(false); // Iniciar transacción

            try (CallableStatement stmt = conexion.prepareCall(sql)) {
                stmt.setString(1, articulo.getCodigo());
                stmt.setString(2, articulo.getDescripcion());
                stmt.setDouble(3, articulo.getPrecioVenta());
                stmt.setDouble(4, articulo.getGastosEnvio());
                stmt.setInt(5, articulo.getTiempoPreparacion());
                stmt.execute();
            }

            conexion.commit(); // Confirmar cambios
            System.out.println("✅ Artículo insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conexion.rollback();
                System.err.println("⚠️ Error al insertar artículo. Se hizo rollback.");
            } catch (SQLException ex) {
                System.err.println("❌ Error al hacer rollback: " + ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                conexion.setAutoCommit(true); // Restaurar autocommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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
            System.err.println("Error al listar artículos: " + e.getMessage());
        }
        return lista;
    }

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
