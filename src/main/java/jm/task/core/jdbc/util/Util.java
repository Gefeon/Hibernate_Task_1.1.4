package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?verifyServerCertificate=false&useSSL=true";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root1";

    private static volatile Util instance;
    private static Connection connection = null;

    private Util() {
    }

    public static Util getInstance() {
        Util singleInstance = instance;
        if (singleInstance == null) {
            synchronized (Util.class) {
                singleInstance = instance;
                if (singleInstance == null) {
                    instance = singleInstance = new Util();
                }
            }
        }
        return singleInstance;
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Class.forName(DRIVER);
            if (!connection.isClosed()) {
                System.out.println("DB connection established");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DB connection not established");
            e.printStackTrace();
        }
        return connection;
    }
}
