package ua.goit.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnectionManager.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
