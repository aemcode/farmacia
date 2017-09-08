/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aemcode.farmacia.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Andre
 */
public class HibernateContextOnOff implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent on) {
        HibernateUtil.getCriarSessao().openSession();
    }

    @Override
    public void contextDestroyed(ServletContextEvent off) {
        HibernateUtil.getCriarSessao().close();
    }
    
}
