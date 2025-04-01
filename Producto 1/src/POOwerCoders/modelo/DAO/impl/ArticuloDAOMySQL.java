package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.ConexionDB;
import POOwerCoders.modelo.DAO.ArticuloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAOMySQL implements ArticuloDAO {

    private Connection conexion = ConexionDB.getConnection();

    @Override
    public void insertar(Articulo articulo) {
        String sql = "INSERT INTO Articulo (codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, articulo.getCodigo());
            stmt.setString(2, articulo.getDescripcion());
            stmt.setDouble(3, articulo.getPrecioVenta());
            stmt.setDouble(4, articulo.getGastosEnvio());
            stmt.setInt(5, articulo.getTiempoPreparacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar artículo: " + e.getMessage());
        }
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
}
