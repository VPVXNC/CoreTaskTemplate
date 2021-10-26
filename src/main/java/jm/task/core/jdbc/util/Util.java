package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class Util {

    public static void main(String[] args) throws SQLException {

        Class<Driver> driverClass = Driver.class;

        try (Connection connection = ConnectionManager.get()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }

    public static final class ConnectionManager {

        private static final String PASSWORD = "root";
        private static final String USERNAME = "root";
        private static final String URL = "jdbc:mysql://localhost:3316/javamentor";


        private ConnectionManager() {
        }

        public static Connection get() {
            try {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}