package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.Articulo;
import com.poowercoders.POOwerCoders.modelo.DAO.ArticuloDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ArticuloDAOHibernate implements ArticuloDAO {

    @Override
    public void insertar(Articulo articulo) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(articulo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error al insertar artículo: " + e.getMessage());
        }
    }

    @Override
    public Articulo buscarPorCodigo(String codigo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Articulo.class, codigo);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Articulo> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Articulo").list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Articulo> buscarPorRangoPrecio(double min, double max) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Articulo a WHERE a.precioVenta BETWEEN :min AND :max")
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Articulo> buscarPorDescripcion(String palabraClave) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Articulo a WHERE a.descripcion LIKE :desc")
                    .setParameter("desc", "%" + palabraClave + "%")
                    .list();
        }
    }
}
