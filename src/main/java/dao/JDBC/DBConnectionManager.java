package dao.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *     class for creating connection to DB with url for connection, username, user password, driver name.
 * it can return connection object. use it for set connection object as context attribute for usage in servlets.
 */
public class DBConnectionManager {

    Connection connection;

    public DBConnectionManager(String dbURL, String userName, String userPassword, String driverName)
    throws ClassNotFoundException, SQLException {

        Class.forName(driverName);
        this.connection = DriverManager.getConnection(dbURL, userName, userPassword);
    }

    public Connection getConnection() {
        return connection;
    }
}
