package com.poowercoders.POOwerCoders.modelo.DAO.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

/**
 * Clase utilitaria para obtener la sesión de Hibernate.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // nombre del archivo en resources
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("❌ Error al crear SessionFactory de Hibernate: " + ex);
            throw new ExceptionInInitializerError("Error al inicializar Hibernate: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
