package com.poowercoders.POOwerCoders.modelo.DAO;

import com.poowercoders.POOwerCoders.modelo.DAO.impl.*; // Importa todos los DAO

public class DAOFactory {

    // Cambia esta constante a "MYSQL" para usar las versiones JDBC tradicionales
    private static final String IMPLEMENTACION = "HIBERNATE"; // o "MYSQL" cambiando la línea por ----> private static final String IMPLEMENTACION = "MYSQL";


    public static ClienteDAO getClienteDAO() {
        if (IMPLEMENTACION.equalsIgnoreCase("HIBERNATE")) {
            return new ClienteDAOHibernate();
        } else {
            return new ClienteDAOMySQL();
        }
    }

    public static ArticuloDAO getArticuloDAO() {
        if (IMPLEMENTACION.equalsIgnoreCase("HIBERNATE")) {
            return new ArticuloDAOHibernate();
        } else {
            return new ArticuloDAOMySQL();
        }
    }

    public static PedidoDAO getPedidoDAO() {
        if (IMPLEMENTACION.equalsIgnoreCase("HIBERNATE")) {
            return new PedidoDAOHibernate();
        } else {
            return new PedidoDAOMySQL();
        }
    }
}
