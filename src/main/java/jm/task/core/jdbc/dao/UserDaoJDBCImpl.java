package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util untilNew = new Util();
    private Statement statemnt;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS new_table " +
                "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                "lastname VARCHAR(70) not NULL, age TINYINT, " +
                "PRIMARY KEY (id))";
        try {
            statemnt = untilNew.getConnect().createStatement();
            statemnt.executeUpdate(sqlCreate);
            System.out.println("Table is added success");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE new_table";
            try {
                statemnt = untilNew.getConnect().createStatement();
                statemnt.execute(sqlDrop);
                System.out.println("Table Droped");//    operation(SQLQueryCommand.DROP_USER_TABLE);
                } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statemnt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSql = "INSERT new_table (name, lastName, age)" +
                "VALUES ('"+ name + "', " + "'" + lastName + "', " + age + ")";
        try {
            statemnt = untilNew.getConnect().createStatement();
            statemnt.executeUpdate(saveSql);
            System.out.println("User " + name + "added to DBaseSaved");
        } catch (SQLException e) {

        }

    }

    public void removeUserById(long id) {
        String deleteSql = "DELETE FROM new_table WHERE id = " + id;

        try {
            statemnt = untilNew.getConnect().createStatement();
            statemnt.executeUpdate(deleteSql);
            System.out.println("Удален User c id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM new_table";

        try {
            statemnt = untilNew.getConnect().createStatement();
            ResultSet rs = statemnt.executeQuery(sqlSelect);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setAge(rs.getByte(3));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

      public void cleanUsersTable() {
        String truncateSql = "TRUNCATE TABLE new_table";

        try {
            statemnt = untilNew.getConnect().createStatement();
            statemnt.execute(truncateSql);
            System.out.println("Table clear");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statemnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*private PreparedStatement getStatement(String sqlQuery) {
        Connection connection = CONNECTOR.getConnection();

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CONNECTOR.putBack(connection);
        return statement;
        //return null;
    }
*/
   /* private void operation(String sqlQuery) {

        try (PreparedStatement statement = getStatement(sqlQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/



  /*  private void insertOperation(String sqlQuery, String name,
                                 String lastName, Byte age) {

        try (PreparedStatement statement = getStatement(sqlQuery)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            statement.executeUpdate();
            System.out.println("User c именем - " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOperation(String sqlQuery, long id) {

        try (PreparedStatement statement = getStatement(sqlQuery)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExist() {
        Connection conect = DriverManager.getConnection();
        DriverManager.putBack(conect);
        DatabaseMetaData databaseMetaData = null;
        try {
            databaseMetaData = conect.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = databaseMetaData.getTables(null, null,
                "users.user", null)) {

            if (rs.next() && rs.next() && rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }*/
}
