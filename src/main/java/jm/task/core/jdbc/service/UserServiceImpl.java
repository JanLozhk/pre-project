package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl users = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {
    }

    @Transactional
    public void createUsersTable() {
        users.createUsersTable();
    }

    @Transactional
    public void dropUsersTable() {
        users.dropUsersTable();
    }

    @Transactional
    public void saveUser(String name, String lastName, byte age) {
        users.saveUser(name, lastName, age);
    }

    @Transactional
    public void removeUserById(long id) {
        users.removeUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return users.getAllUsers();
    }

    @Transactional
    public void cleanUsersTable() {
        users.cleanUsersTable();
    }
}
