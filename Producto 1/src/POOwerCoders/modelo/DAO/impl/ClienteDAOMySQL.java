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
        try {
            conn.setAutoCommit(false); // 🔐 Iniciar transacción

            if (cliente instanceof ClientePremium cp) {
                String sql = "{CALL sp_insertar_cliente_premium(?, ?, ?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    stmt.setString(1, cp.getNif());
                    stmt.setString(2, cp.getNombre());
                    stmt.setString(3, cp.getDomicilio());
                    stmt.setString(4, cp.getEmail());
                    stmt.setDouble(5, cp.getCuotaAnual());
                    stmt.setDouble(6, cp.getDescuentoEnvio());
                    stmt.execute();
                }

            } else if (cliente instanceof ClienteEstandar ce) {
                String sql = "{CALL sp_insertar_cliente_estandar(?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    stmt.setString(1, ce.getNif());
                    stmt.setString(2, ce.getNombre());
                    stmt.setString(3, ce.getDomicilio());
                    stmt.setString(4, ce.getEmail());
                    stmt.execute();
                }
            }

            conn.commit(); // ✅ Confirmar cambios
            System.out.println("✅ Cliente insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conn.rollback(); // ❌ Deshacer si algo falla
                System.err.println("⚠️ Error al insertar cliente. Se realizó rollback.");
            } catch (SQLException ex) {
                System.err.println("❌ Error durante rollback: " + ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Restaurar autocommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
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


