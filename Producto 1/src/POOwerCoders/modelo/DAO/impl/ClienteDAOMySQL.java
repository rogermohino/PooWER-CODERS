package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {

    private Connection conn = ConexionDB.getConnection();

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nif, nombre, domicilio, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNif());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDomicilio());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String nif) {
        String sql = "DELETE FROM Cliente WHERE nif = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nif);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorNif(String nif) {
        String sql = "SELECT * FROM Cliente WHERE nif = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nif);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("nombre"),
                        rs.getString("domicilio"),
                        rs.getString("nif"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("domicilio"),
                        rs.getString("nif"),
                        rs.getString("email")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }
}


