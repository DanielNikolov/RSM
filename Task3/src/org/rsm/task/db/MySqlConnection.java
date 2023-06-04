package org.rsm.task.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private String className;
    private String connectionUrl;
    private String user;
    private String password;

    public MySqlConnection(String connectionUrl, String dbUser, String dbPassword) {
        this.className = "com.mysql.jdbc.Driver";
        this.connectionUrl = connectionUrl;
        this.user = dbUser;
        this.password = dbPassword;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(this.className);
            connection = DriverManager.getConnection(this.connectionUrl, this.user, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
