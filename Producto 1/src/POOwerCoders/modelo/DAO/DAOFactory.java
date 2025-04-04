package POOwerCoders.modelo.DAO;

// Importamos la implementación concreta de ClienteDAO
import POOwerCoders.modelo.DAO.impl.ClienteDAOMySQL;

/**
 * Clase DAOFactory.
 * Esta clase implementa el patrón Factory y se encarga de crear instancias
 * de las interfaces DAO (ClienteDAO, ArticuloDAO, PedidoDAO).
 *
 * Su propósito es desacoplar la lógica de negocio de las implementaciones
 * concretas de acceso a datos, facilitando así el mantenimiento y la escalabilidad del sistema.
 */
public class DAOFactory {

    /**
     * Devuelve una instancia de ClienteDAO usando su implementación concreta en MySQL.
     *
     * @return ClienteDAO (ClienteDAOMySQL)
     */
    public static POOwerCoders.modelo.DAO.ClienteDAO getClienteDAO() {
        return new ClienteDAOMySQL();
    }

    /**
     * Devuelve una instancia de ArticuloDAO usando su implementación concreta en MySQL.
     *
     * @return ArticuloDAO (ArticuloDAOMySQL)
     */
    public static POOwerCoders.modelo.DAO.ArticuloDAO getArticuloDAO() {
        return new POOwerCoders.modelo.DAO.impl.ArticuloDAOMySQL();
    }

    /**
     * Devuelve una instancia de PedidoDAO usando su implementación concreta en MySQL.
     *
     * @return PedidoDAO (PedidoDAOMySQL)
     */
    public static POOwerCoders.modelo.DAO.PedidoDAO getPedidoDAO() {
        return new POOwerCoders.modelo.DAO.impl.PedidoDAOMySQL();
    }

}
