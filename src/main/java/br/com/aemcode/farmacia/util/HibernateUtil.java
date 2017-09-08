package br.com.aemcode.farmacia.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory session = criarSessao();

    public static SessionFactory getCriarSessao() {
        return session;
    }

    private static SessionFactory criarSessao() {

        try {

            Configuration configuracao = new Configuration().configure("hibernate.cfg.xml");

            SessionFactory sessionFactory = configuracao.buildSessionFactory();

            return sessionFactory;

        } catch (Throwable ex) {

            System.err.println("A sessão não pode ser criada." + ex);

            throw new ExceptionInInitializerError(ex);

        }
    }

}
