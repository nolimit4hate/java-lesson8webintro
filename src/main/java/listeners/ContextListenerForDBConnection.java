package listeners;

import dao.JDBC.DBConnectionManager;
import dao.user.DAOUser;
import dao.user.impl.AllUsersTemp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;


/**
 *      this context listener is used for making connection to DB. it gets data about connection(url, name,
 * password) from context init parameters which setting on web.xml as dbUser, dbPassword, dbURL, dbClassName.
 */
@WebListener
public class ContextListenerForDBConnection implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // get connection parameters from context and create connection
        String dbURL = context.getInitParameter("dbURL");
        String dbUserName = context.getInitParameter("dbUser");
        String dbUserPassword = context.getInitParameter("dbPassword");
        String dbDriverName = context.getInitParameter("dbDriverName");

        try {
            DBConnectionManager connectionManager = new DBConnectionManager(
                    dbURL, dbUserName, dbUserPassword, dbDriverName);
            DAOUser allUsers = new AllUsersTemp(connectionManager.getConnection());
            context.setAttribute("allusers", allUsers);
            System.out.println("#contextListener# Connected to DB. Set 'allusers' attribute to context");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DAOUser allUsers = (DAOUser) sce.getServletContext().getAttribute("allusers");
        allUsers.closeDBConnection();
        System.out.println("#contextListener# Database lost connection");
    }
}
