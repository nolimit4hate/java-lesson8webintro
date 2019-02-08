package listener;

import DBManager.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
//        ServletContext context = servletContextEvent.getServletContext();
//
//        String url = context.getInitParameter("DBURL");
//        String u = context.getInitParameter("DBUSER");
//        String p = context.getInitParameter("DBPWD");
//
//        //create database connection from init parameters and set it to context
//        DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
//        context.setAttribute("DBManager", dbManager);
//        System.out.println("Database connection initilized for Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
//        ServletContext context = servletContextEvent.getServletContext();
//        DBConnectionManager dbManager = (DBConnectionManager) context.getAttribute("DBManager");
//        dbManager.closeConnection();
//        System.out.println("Database connection closed for application ");
    }

}
