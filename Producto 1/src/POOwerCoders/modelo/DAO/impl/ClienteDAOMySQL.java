package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.ConexionBD;
import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;
import POOwerCoders.modelo.DAO.DAOFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {

    private Connection conn = POOwerCoders.modelo.ConexionBD.getConnection();

    public ClienteDAOMySQL() {
        System.out.println("🛠 Obteniendo conexión desde ConexionBD...");
        this.conn = ConexionBD.getConnection();

        if (this.conn == null) {
            System.err.println("❌ La conexión es NULL. Verifica la configuración de ConexionBD.");
        } else {
            System.out.println("✅ Conexión recibida correctamente en ClienteDAOMySQL.");
        }
    }

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nif, nombre, domicilio, email, tipoCliente) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNif());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDomicilio());
            stmt.setString(4, cliente.getEmail());

            if (cliente instanceof ClientePremium) {
                stmt.setString(5, "Premium");
            } else {
                stmt.setString(5, "Estandar");
            }

            stmt.executeUpdate();

            // Insertar en tabla correspondiente
            if (cliente instanceof ClientePremium cp) {
                String sqlPremium = "INSERT INTO premium (nif, cuotaAnual, descuentoEnvio) VALUES (?, ?, ?)";
                try (PreparedStatement stmtPremium = conn.prepareStatement(sqlPremium)) {
                    stmtPremium.setString(1, cp.getNif());
                    stmtPremium.setDouble(2, cp.getCuotaAnual());
                    stmtPremium.setDouble(3, cp.getDescuentoEnvio());
                    stmtPremium.executeUpdate();
                }
            } else if (cliente instanceof ClienteEstandar ce) {
                String sqlEstandar = "INSERT INTO estandar (nif) VALUES (?)";
                try (PreparedStatement stmtEstandar = conn.prepareStatement(sqlEstandar)) {
                    stmtEstandar.setString(1, ce.getNif());
                    stmtEstandar.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
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
                String tipo = rs.getString("tipoCliente");
                Cliente cliente;

                if ("Premium".equalsIgnoreCase(tipo)) {
                    cliente = new ClientePremium(
                            rs.getString("nombre"),
                            rs.getString("domicilio"),
                            rs.getString("nif"),
                            rs.getString("email")
                    );
                } else {
                    cliente = new ClienteEstandar(
                            rs.getString("nombre"),
                            rs.getString("domicilio"),
                            rs.getString("nif"),
                            rs.getString("email")
                    );
                }

                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }
    @Override
    public List<ClienteEstandar> listarClientesEstandar() {
        List<ClienteEstandar> estandar = new ArrayList<>();
        for (Cliente c : listarTodos()) {
            if (c instanceof ClienteEstandar) {
                estandar.add((ClienteEstandar) c);
            }
        }
        return estandar;
    }
    @Override
    public List<ClientePremium> listarClientesPremium() {
        List<ClientePremium> premium = new ArrayList<>();
        for (Cliente c : listarTodos()) {
            if (c instanceof ClientePremium) {
                premium.add((ClientePremium) c);
            }
        }
        return premium;
    }
}


