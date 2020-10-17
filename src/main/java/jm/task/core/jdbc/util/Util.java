package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Util {
    private static SessionFactory sessionFactory;
    private static Connection connect;
    private static final String URL = "jdbc:mysql://localhost:3306/testshema?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    /*public static final class HibernateConnection {
        private static SessionFactory sessionFactory;
*/
        public static SessionFactory getSessionFactory() {

            if (sessionFactory == null || sessionFactory.isClosed()) {
                try {
                    Configuration config = new Configuration();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("db_config");
                    Properties setting = new Properties();
                    setting.put(Environment.DRIVER, resourceBundle.getString("hibernate.connection.driver_class"));
                    setting.put(Environment.URL, resourceBundle.getString("hibernate.connection.url"));
                    setting.put(Environment.USER, resourceBundle.getString("hibernate.connection.username"));
                    setting.put(Environment.PASS, resourceBundle.getString("hibernate.connection.password"));
                    setting.put(Environment.DIALECT, resourceBundle.getString("hibernate.dialect"));
                    setting.put(Environment.SHOW_SQL, resourceBundle.getString("hibernate.show_sql"));
                /*    setting.put(Environment.FORMAT_SQL, resourceBundle.getString("hibernate.format_sql"));
                    setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, resourceBundle.getString("hibernate.current_session_context_class"));*/
                    config.setProperties(setting);

                    config.addAnnotatedClass(User.class);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                            .applySettings(config.getProperties());
                    sessionFactory = config.buildSessionFactory(builder.build());
                } catch (Exception e) {
                    System.out.println("ExceptionConnectionHibernate!" + e);
                }
            }
            return sessionFactory;
        }

    public static Connection getConnect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                //Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);  было так!
                connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connect.setAutoCommit(false);
            } catch (SQLException e) {
                connect.rollback();
                e.printStackTrace();
            }
        }
        return connect;
    }
}

/*#for JDBC

db.url=jdbc:mysql://localhost:3306/mytest.db?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
db.login=root
db.pass=admin
db.driver.name=com.mysql.cj.jdbc.Driver

#for hibernate

driver_class=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mytest.db?useSSL=false&serverTimezone=UTC
username=root
password=admin
dialect=org.hibernate.dialect.MySQLDialect
show_sql=true
format_sql=true
current_session=thread*/
