package main.java.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, отвечающий за подключение к базе данных
 */
public class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.username";
    private static final String PASS_KEY = "db.password";

    /*static {
        loadDriver();
    }*/

    private ConnectionManager() {
    }

    private static void loadDriver() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Проблема с JDBC драйвером!");
        }
    }


    public static Connection open() {
        try {
            return DriverManager.getConnection(
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
