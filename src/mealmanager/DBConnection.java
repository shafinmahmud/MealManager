package mealmanager;

import java.sql.*;

/**
 *
 * @author SHAFIN
 */
public class DBConnection {

    /**
     *
     */
    private Connection connection;

    /**
     * constructor for mysql connection
     * @param url
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection ConnectMySQL(String url, String username, String password) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }

    /**
     * connection for sqlite
     * @param url
     * @return
     * @throws SQLException
     */
    public Connection ConnectSQLite(String url) throws SQLException {

        connection = DriverManager.getConnection(url);

        return connection;
    }

}
