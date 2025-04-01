package PO0werCoders.modelo.dao;

import PO0werCoders.modelo.dao.impl.ClienteDAOMySQL;

public class DAOFactory {
    public static PO0werCoders.modelo.dao.ClienteDAO getClienteDAO() {
        return new ClienteDAOMySQL();
    }
    public static PO0werCoders.modelo.dao.ArticuloDAO getArticuloDAO() {
        return new PO0werCoders.modelo.dao.impl.ArticuloDAOMySQL();
    }
    public static PO0werCoders.modelo.dao.PedidoDAO getPedidoDAO() {
        return new POOwerCoders.modelo.dao.impl.PedidoDAOMySQL();
    }



    // En el futuro puedes tener: getArticuloDAO(), getPedidoDAO(), etc.
}

