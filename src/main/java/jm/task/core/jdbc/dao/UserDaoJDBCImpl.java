package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //Util untilNew = new Util();
 //   private Statement statemnt;

    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS new_table " +
                "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                "lastname VARCHAR(70) not NULL, age TINYINT, " +
                "PRIMARY KEY (id))";
        try (Connection connct = Util.getConnect();
             Statement statemnt = connct.createStatement()) {
            statemnt.executeUpdate(sqlCreate);
            System.out.println("Table is added success");
            connct.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally ok");
        }
    }


    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE new_table";
        try (Connection conn = Util.getConnect();
             Statement statemnt = conn.createStatement()) {
            statemnt.execute(sqlDrop);
            System.out.println("Table Droped");//
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
 /*       } finally {*/
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "INSERT new_table (name, lastName, age)" +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";
        try (Connection conn = Util.getConnect();
             Statement statemnt = conn.createStatement()) {
            statemnt.executeUpdate(saveSql);
            conn.commit();
            System.out.println("User " + name + "added to DBaseSaved");
        } catch (SQLException e) {

        }

    }

    public void removeUserById(long id) {
        String deleteSql = "DELETE FROM new_table WHERE id = " + id;

        try (Connection conn = Util.getConnect();
             Statement statemnt = conn.createStatement()) {
            statemnt.executeUpdate(deleteSql);
            System.out.println("Удален User c id = " + id);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
/*        } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM new_table";

        try (Connection conn = Util.getConnect();
             Statement statemnt = conn.createStatement()) {
            ResultSet rs = statemnt.executeQuery(sqlSelect);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setAge(rs.getByte(3));
                list.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
 /*       } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        return list;
    }

    public void amplcleanUsersTable() {
        String truncateSql = "TRUNCATE TABLE new_table";

        try (Connection conn = Util.getConnect()) {
            try (Statement statemnt = Util.getConnect().createStatement()) {
                statemnt.execute(truncateSql);
                System.out.println("Table clear");
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
 /*       } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            }
        } catch (Exception e) {

        }
    }
}
