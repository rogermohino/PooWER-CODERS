package POOwerCoders.modelo.DAO.impl;

// Importación de las clases necesarias del proyecto y JDBC
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

/**
 * Clase que implementa la interfaz PedidoDAO y se encarga de realizar
 * las operaciones de acceso a datos (CRUD) para los pedidos,
 * utilizando una base de datos MySQL a través de JDBC.
 */
public class PedidoDAOMySQL implements PedidoDAO {

    // Conexión a la base de datos obtenida desde la clase ConexionBD
    private Connection conn = POOwerCoders.modelo.ConexionBD.getConnection();

    // DAOs auxiliares para cargar objetos Cliente y Articulo completos
    private ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
    private ArticuloDAO articuloDAO = DAOFactory.getArticuloDAO();

    /**
     * Inserta un nuevo pedido en la base de datos.
     * Se utiliza una sentencia preparada para prevenir SQL Injection.
     */
    @Override
    public void insertar(Pedido pedido) {
        String sql = "INSERT INTO Pedido (numeroPedido, nifCliente, codigoArticulo, cantidad, fechaHora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getNumeroPedido());
            stmt.setString(2, pedido.getCliente().getNif());
            stmt.setString(3, pedido.getArticulo().getCodigo());
            stmt.setInt(4, pedido.getCantidad());
            stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora())); // Convertimos LocalDateTime a Timestamp
            stmt.executeUpdate(); // Ejecutamos la inserción
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
        }
    }

    /**
     * Busca un pedido por su número en la base de datos.
     * Carga también el cliente y artículo relacionados mediante sus respectivos DAOs.
     */
    @Override
    public Pedido buscarPorNumero(int numeroPedido) {
        String sql = "SELECT * FROM Pedido WHERE numeroPedido = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroPedido);
            ResultSet rs = stmt.executeQuery(); // Ejecutamos la consulta
            if (rs.next()) {
                // Obtenemos cliente y artículo completo
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente"));
                Articulo articulo = articuloDAO.buscarPorCodigo(rs.getString("codigoArticulo"));

                // Creamos y devolvemos el pedido
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

    /**
     * Devuelve una lista con todos los pedidos almacenados en la base de datos.
     */
    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql); // Ejecutamos la consulta
            while (rs.next()) {
                // Cargamos cliente y artículo asociados
                Cliente cliente = clienteDAO.buscarPorNif(rs.getString("nifCliente"));
                Articulo articulo = articuloDAO.buscarPorCodigo(rs.getString("codigoArticulo"));

                // Creamos el objeto Pedido y lo agregamos a la lista
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

    /**
     * Elimina un pedido de la base de datos según su número de pedido.
     */
    @Override
    public void eliminar(int numeroPedido) {
        String sql = "DELETE FROM Pedido WHERE numeroPedido = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroPedido);
            stmt.executeUpdate(); // Ejecutamos la eliminación
            System.out.println("🗑️ Pedido eliminado de la base de datos.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el pedido: " + e.getMessage());
        }
    }

}
