package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl users = new UserDaoHibernateImpl();
    //UserDaoJDBCImpl users = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {
    }

    public void createUsersTable() {
        users.createUsersTable();
    }

    public void dropUsersTable() {
        users.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        users.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        users.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return users.getAllUsers();
    }

    public void cleanUsersTable() {
        users.cleanUsersTable();
    }
}
