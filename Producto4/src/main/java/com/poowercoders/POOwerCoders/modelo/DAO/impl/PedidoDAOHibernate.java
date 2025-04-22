package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.DAO.PedidoDAO;
import com.poowercoders.POOwerCoders.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PedidoDAOHibernate implements PedidoDAO {

    @Override
    public void insertar(Pedido pedido) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(pedido);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error al insertar pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorNumero(int numeroPedido) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pedido.class, numeroPedido);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pedido> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido").list();
        }
    }

    @Override
    public void eliminar(int numeroPedido) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Pedido pedido = session.get(Pedido.class, numeroPedido);
            if (pedido != null) {
                session.remove(pedido);
                tx.commit();
            } else {
                System.out.println("⚠️ Pedido con número " + numeroPedido + " no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error al eliminar pedido: " + e.getMessage());
        }
    }
}
