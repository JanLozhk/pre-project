package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mytest.db?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    private Connection connect;

    public Util() {
        try{
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            connect.createStatement().execute("SELECT * FROM new_table");
            System.out.println("util.conct., all is ok");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("conect error???");
        }
    }

    public Connection getConnect() {
        return connect;
    }
    // реализуйте настройку соеденения с БД
}
