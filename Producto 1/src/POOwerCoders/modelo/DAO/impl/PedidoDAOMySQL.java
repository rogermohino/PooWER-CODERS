package POOwerCoders.modelo.dao.impl;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.Pedido;
import POOwerCoders.modelo.ConexionDB;



import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOMySQL implements PO0werCoders.modelo.dao.PedidoDAO {

    private Connection conn = ConexionDB.getConnection();
    private PO0werCoders.modelo.dao.ClienteDAO clienteDAO = PO0werCoders.modelo.dao.DAOFactory.getClienteDAO(); // usamos el DAO para obtener el cliente completo

    @Override
    public void insertar(Pedido pedido) {
        String sql = "INSERT INTO Pedido (numeroPedido, nifCliente, codigoArticulo, cantidad, fechaHora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getNumeroPedido());
            stmt.setString(2, pedido.getCliente().getNif());  // extraemos el NIF desde el objeto Cliente
            stmt.setString(3, pedido.getArticulo().getCodigo());
            stmt.setInt(4, pedido.getCantidad());
            stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorNumero(int numeroPedido) {
        String sql = "SELECT * FROM Pedido WHERE numeroPedido = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroPedido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente")); // cargamos el objeto Cliente completo

                return new Pedido(
                        rs.getInt("numeroPedido"),
                        cliente,
                        rs.getString("codigoArticulo"),
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
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente"));

                Pedido p = new Pedido(
                        rs.getInt("numeroPedido"),
                        cliente,
                        rs.getString("codigoArticulo"),
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
}


