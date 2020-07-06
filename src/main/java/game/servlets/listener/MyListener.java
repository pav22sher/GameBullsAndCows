package game.servlets.listener;

import game.dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class manages EntityManager
 * @autor Scherbakov Pavel
 * @version 2.1
 */

public class MyListener implements ServletContextListener {
    private EntityManager em;
    private EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //data is stored in a file C:/data/test
        //emf = Persistence.createEntityManagerFactory("h2_permanent");
        //data is stored in temporary memory
        emf = Persistence.createEntityManagerFactory("h2_temporary");
        em = emf.createEntityManager();
        UserDao dao=new UserDao(em);
        servletContextEvent.getServletContext().setAttribute("dao",dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        em.close();
        emf.close();
    }
}
