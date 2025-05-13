package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.ClienteEstandar;
import com.poowercoders.POOwerCoders.modelo.ClientePremium;
import com.poowercoders.POOwerCoders.modelo.ConexionBD;
import com.poowercoders.POOwerCoders.modelo.DAO.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO para la entidad Cliente.
 * Utiliza procedimientos almacenados, manejo de transacciones,
 * y separación de tipos de cliente (Premium y Estándar).
 */
public class ClienteDAOMySQL implements ClienteDAO {

    private final Connection conn;

    /**
     * Constructor: obtiene la conexión desde la clase de utilidad ConexionBD.
     */
    public ClienteDAOMySQL() {
        System.out.println("🛠 Obteniendo conexión desde ConexionBD...");
        this.conn = ConexionBD.getConnection();

        if (this.conn == null) {
            System.err.println("❌ La conexión es NULL. Verifica la configuración de ConexionBD.");
        } else {
            System.out.println("✅ Conexión recibida correctamente en ClienteDAOMySQL.");
        }
    }

    /**
     * Inserta un cliente en la base de datos utilizando procedimientos almacenados.
     * Si es un cliente premium, se llama a un procedimiento específico.
     * Si es estándar, se llama a otro procedimiento.
     *
     * @param cliente Cliente a insertar.
     */
    @Override
    public void insertar(Cliente cliente) {
        try {
            conn.setAutoCommit(false); // 🔐 Iniciar transacción manual

            if (cliente instanceof ClientePremium cp) {
                // Procedimiento almacenado para cliente premium
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
                // Procedimiento almacenado para cliente estándar
                String sql = "{CALL sp_insertar_cliente_estandar(?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    stmt.setString(1, ce.getNif());
                    stmt.setString(2, ce.getNombre());
                    stmt.setString(3, ce.getDomicilio());
                    stmt.setString(4, ce.getEmail());
                    stmt.execute();
                }
            }

            conn.commit(); // ✅ Confirmar los cambios si todo fue bien
            System.out.println("✅ Cliente insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conn.rollback(); // ❌ Si algo falla, revertimos todo
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

    /**
     * Elimina un cliente por su NIF.
     *
     * @param nif NIF del cliente a eliminar.
     */
    @Override
    public void eliminar(String nif) {
        String sql = "DELETE FROM Cliente WHERE nif = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nif);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por su NIF.
     *
     * @param nif NIF del cliente.
     * @return Objeto Cliente si se encuentra, o null si no existe.
     */
    @Override
    public Cliente buscarPorNif(String nif) {
        String sql = "SELECT * FROM Cliente WHERE nif = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nif);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipoCliente");

                if ("Premium".equalsIgnoreCase(tipo)) {
                    return new ClientePremium(
                            rs.getString("nombre"),
                            rs.getString("domicilio"),
                            rs.getString("nif"),
                            rs.getString("email")
                    );
                } else {
                    return new ClienteEstandar(
                            rs.getString("nombre"),
                            rs.getString("domicilio"),
                            rs.getString("nif"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todos los clientes de la base de datos.
     *
     * @return Lista de clientes.
     */
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
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Lista solo los clientes estándar.
     *
     * @return Lista de clientes estándar.
     */
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

    /**
     * Lista solo los clientes premium.
     *
     * @return Lista de clientes premium.
     */
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
