package POOwerCoders.modelo.DAO;

import POOwerCoders.modelo.DAO.impl.ClienteDAOMySQL;

public class DAOFactory {
    public static POOwerCoders.modelo.DAO.ClienteDAO getClienteDAO() {
        return new ClienteDAOMySQL();
    }
    public static POOwerCoders.modelo.DAO.ArticuloDAO getArticuloDAO() {
        return new POOwerCoders.modelo.DAO.impl.ArticuloDAOMySQL();
    }
    public static POOwerCoders.modelo.DAO.PedidoDAO getPedidoDAO() {
        return new POOwerCoders.modelo.DAO.impl.PedidoDAOMySQL();
    }
    
}

