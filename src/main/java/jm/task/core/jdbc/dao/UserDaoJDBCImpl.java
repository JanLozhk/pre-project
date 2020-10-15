package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
 //   Util untilNew = new Util();
    //   private Statement statemnt;

    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS new_table " +
                "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                "lastname VARCHAR(70) not NULL, age TINYINT, " +
                "PRIMARY KEY (id))";
        try (Connection connct = Util.getConnect()) {
            try (Statement statemnt = connct.createStatement()) {
                connct.setAutoCommit(false);
                statemnt.executeUpdate(sqlCreate);
                System.out.println("Table is added success");
                connct.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connct.rollback();
            }
                connct.setAutoCommit(true);
        } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE new_table";
        try (Connection conn = Util.getConnect()) {
            try (Statement statemnt = conn.createStatement()) {
                conn.setAutoCommit(false);
                statemnt.execute(sqlDrop);
                System.out.println("Table Droped");//
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                conn.setAutoCommit(true);
            }
        }catch (SQLException throwables) {
                throwables.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "INSERT new_table (name, lastName, age)" +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";

        try (Connection conn = Util.getConnect()) {
            try (Statement statemnt = conn.createStatement()) {
                conn.setAutoCommit(false);
                statemnt.executeUpdate(saveSql);
                conn.commit();
                System.out.println("User " + name + "added to DBaseSaved");
            } catch (SQLException e) {
                e.printStackTrace();
                    conn.rollback();
                }
            conn.setAutoCommit(true);
        } catch (SQLException throwables) {
                    throwables.printStackTrace();
            }
        }

    public void removeUserById(long id) {
        String deleteSql = "DELETE FROM new_table WHERE id = " + id;
        try (Connection conn = Util.getConnect()) {
            try (Statement statemnt = conn.createStatement()) {
                conn.setAutoCommit(false);
                statemnt.executeUpdate(deleteSql);
                System.out.println("Удален User c id = " + id);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                    conn.rollback();
                }
        } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM new_table";

        try (Connection conn = Util.getConnect()) {
            try (Statement statemnt = conn.createStatement()) {
                conn.setAutoCommit(false);
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
                    conn.rollback();
                }
            conn.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return list;
        }

    public void cleanUsersTable() {
        String truncateSql = "TRUNCATE TABLE new_table";
        try (Connection conn = Util.getConnect()) {
            conn.setAutoCommit(false);
            try (Statement statemnt = Util.getConnect().createStatement()) {
                statemnt.execute(truncateSql);
                System.out.println("Table clear");
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e1) {
                e1.printStackTrace();
            }
    }
}
