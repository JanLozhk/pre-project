package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Util {
    private static Connection connect;
    private static final String URL = "jdbc:mysql://localhost:3306/mytest.db?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    //public Util() throws SQLException {
         //getConnct();
        /*    try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            connect.setAutoCommit(false);
//            Savepoint savepoint = connect.setSavepoint();
//            connect.createStatement().execute("SELECT * FROM new_table");
            connect.commit();

            System.out.println("util.conct., all is ok");

        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
            System.out.println("conect error???");
        }*/
    //}

    public static Connection getConnect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connect.setAutoCommit(false);
            connect.commit();
        }
        return connect;
    }
}