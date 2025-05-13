package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.modelo.ClienteEstandar;
import com.poowercoders.POOwerCoders.modelo.ClientePremium;
import com.poowercoders.POOwerCoders.modelo.DAO.ClienteDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteDAOHibernate implements ClienteDAO {

    @Override
    public void insertar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String nif) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, nif);
            if (cliente != null) {
                session.remove(cliente);
                tx.commit();
            } else {
                System.out.println("⚠️ Cliente con NIF " + nif + " no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorNif(String nif) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, nif);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cliente> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente").list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ClienteEstandar> listarClientesEstandar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClienteEstandar").list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ClientePremium> listarClientesPremium() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClientePremium").list();
        }
    }
}
