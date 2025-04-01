package POOwerCoders.modelo.DAO.impl;

import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.Pedido;
import POOwerCoders.modelo.ConexionDB;
import POOwerCoders.modelo.DAO.ArticuloDAO;
import POOwerCoders.modelo.DAO.ClienteDAO;
import POOwerCoders.modelo.DAO.DAOFactory;
import POOwerCoders.modelo.DAO.PedidoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOMySQL implements PedidoDAO {

    private Connection conn = ConexionDB.getConnection();
    private ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
    private ArticuloDAO articuloDAO = DAOFactory.getArticuloDAO();  // DAO para obtener el artículo completo

    @Override
    public void insertar(Pedido pedido) {
        String sql = "INSERT INTO Pedido (numeroPedido, nifCliente, codigoArticulo, cantidad, fechaHora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getNumeroPedido());
            stmt.setString(2, pedido.getCliente().getNif());
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
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
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
}
