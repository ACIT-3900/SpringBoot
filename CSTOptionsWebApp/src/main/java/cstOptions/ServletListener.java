package cstOptions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
	
	@Override
    public void contextInitialized (ServletContextEvent sce) {
        System.out.println("from ServletContextListener: " +
                                     " context initialized");
    }

    @Override
    public void contextDestroyed (ServletContextEvent sce) {
    }
}