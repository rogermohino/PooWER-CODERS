package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.Pedido;
import POOwerCoders.modelo.DAO.ArticuloDAO;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.DAO.PedidoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOMySQL implements PedidoDAO {

    private Connection conn = POOwerCoders.modelo.ConexionBD.getConnection();
    private ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
    private ArticuloDAO articuloDAO = DAOFactory.getArticuloDAO();  // DAO para obtener el artículo completo

    @Override
    public void insertar(Pedido pedido) {
        String sql = "{CALL sp_insertar_pedido(?, ?, ?, ?, ?)}";

        try {
            conn.setAutoCommit(false); // ⛔ Iniciar transacción

            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, pedido.getNumeroPedido());
                stmt.setString(2, pedido.getCliente().getNif());
                stmt.setString(3, pedido.getArticulo().getCodigo());
                stmt.setInt(4, pedido.getCantidad());
                stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora()));
                stmt.execute();
            }

            conn.commit(); // ✅ Confirmar cambios
            System.out.println("✅ Pedido insertado correctamente con procedimiento y commit.");

        } catch (SQLException e) {
            try {
                conn.rollback(); // ❌ Revertir si hay error
                System.err.println("⚠️ Error al insertar pedido. Se realizó rollback.");
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
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente"));
                Articulo articulo = articuloDAO.buscarPorCodigo(rs.getString("codigoArticulo"));

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
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }
        return lista;
    }


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
            System.err.println("Error al buscar pedido: " + e.getMessage());
        }
        return null;
    }


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
