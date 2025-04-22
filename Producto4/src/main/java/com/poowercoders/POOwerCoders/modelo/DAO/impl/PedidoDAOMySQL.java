package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.Articulo;
import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.Pedido;
import com.poowercoders.POOwerCoders.modelo.ConexionBD;
import com.poowercoders.POOwerCoders.modelo.DAO.ArticuloDAO;
import com.poowercoders.POOwerCoders.modelo.DAO.ClienteDAO;
import com.poowercoders.POOwerCoders.modelo.DAO.DAOFactory;
import com.poowercoders.POOwerCoders.modelo.DAO.PedidoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de PedidoDAO.
 * Gestiona los pedidos mediante procedimientos almacenados y consultas SQL.
 */
public class PedidoDAOMySQL implements PedidoDAO {

    private final Connection conn = ConexionBD.getConnection(); // Conexión a la BBDD
    private final ClienteDAO clienteDAO = DAOFactory.getClienteDAO(); // Para reconstruir el cliente del pedido
    private final ArticuloDAO articuloDAO = DAOFactory.getArticuloDAO(); // Para reconstruir el artículo del pedido

    /**
     * Inserta un nuevo pedido utilizando un procedimiento almacenado.
     * Se aplica control de transacciones y rollback si ocurre un error.
     *
     * @param pedido Pedido a insertar.
     */
    @Override
    public void insertar(Pedido pedido) {
        String sql = "{CALL sp_insertar_pedido(?, ?, ?, ?, ?)}";

        try {
            conn.setAutoCommit(false); // 🔐 Iniciar transacción manual

            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, pedido.getNumeroPedido());
                stmt.setString(2, pedido.getCliente().getNif());
                stmt.setString(3, pedido.getArticulo().getCodigo());
                stmt.setInt(4, pedido.getCantidad());
                stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora()));
                stmt.execute();
            }

            conn.commit(); // ✅ Confirmar cambios si todo salió bien
            System.out.println("✅ Pedido insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conn.rollback(); // ❌ Deshacer si hubo error
                System.err.println("⚠️ Error al insertar pedido. Se realizó rollback.");
            } catch (SQLException ex) {
                System.err.println("❌ Error durante rollback: " + ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Restaurar comportamiento por defecto
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lista todos los pedidos registrados en la base de datos.
     * Reconstruye los objetos completos (Pedido, Cliente y Artículo).
     *
     * @return Lista de objetos Pedido.
     */
    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente")); // Buscar cliente completo
                Articulo articulo = articuloDAO.buscarPorCodigo(rs.getString("codigoArticulo")); // Buscar artículo completo

                Pedido p = new Pedido(
                        rs.getInt("numeroPedido"),
                        cliente,
                        articulo,
                        rs.getInt("cantidad"),
                        rs.getTimestamp("fechaHora").toLocalDateTime()
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca un pedido específico por su número.
     * Devuelve el objeto Pedido si se encuentra.
     *
     * @param numeroPedido Número del pedido a buscar.
     * @return Pedido encontrado o null si no existe.
     */
    @Override
    public Pedido buscarPorNumero(int numeroPedido) {
        String sql = "SELECT * FROM Pedido WHERE numeroPedido = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente"));
                Articulo articulo = articuloDAO.buscarPorCodigo(rs.getString("codigoArticulo"));

                return new Pedido(
                        rs.getInt("numeroPedido"),
                        cliente,
                        articulo,
                        rs.getInt("cantidad"),
                        rs.getTimestamp("fechaHora").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar pedido: " + e.getMessage());
        }

        return null;
    }

    /**
     * Elimina un pedido de la base de datos por su número.
     *
     * @param numeroPedido Número del pedido a eliminar.
     */
    @Override
    public void eliminar(int numeroPedido) {
        String sql = "DELETE FROM Pedido WHERE numeroPedido = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroPedido);
            stmt.executeUpdate();
            System.out.println("🗑️ Pedido eliminado de la base de datos.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el pedido: " + e.getMessage());
        }
    }
}
