package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection connect;
    private static final String URL = "jdbc:mysql://localhost:3306/mytest.db?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                connect.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}