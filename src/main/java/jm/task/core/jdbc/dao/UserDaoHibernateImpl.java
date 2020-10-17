package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //private final SessionFactory factorySession = Util.HibernateConnection.getSessionFactory();
    //private final SessionFactory factorySession = Util.getSessionFactory();
    private static final String SQL_QUERY = "CREATE TABLE IF NOT EXISTS testshema.new_table " +
            "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
            "lastName VARCHAR(70) not NULL, age TINYINT, " +
            "PRIMARY KEY (id))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS testshema.new_table;";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        operation(SQL_QUERY);
    }

    @Override
    public void dropUsersTable() {
       operation(DROP_TABLE);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            User user = new User(name, lastName, age);

            Session session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            session.save(user);
            tr.commit();
            session.getSessionFactory().close();
        }

    @Override
    public void removeUserById(long id) {
        User user = new User();// session.get(User.class, id);
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.getSessionFactory().close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        tr.commit();
        session.getSessionFactory().close();
        return list;
    }

    //@Transactional()
    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        for(User usr: list) {
            session.delete(usr);
        }
        tr.commit();
        session.getSessionFactory().close();
    }

    private void operation(String sqlQuery) {
            Session session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            NativeQuery query = session.createSQLQuery(sqlQuery); //executeUpdate();
            query.executeUpdate(); //
            tr.commit();
        session.getSessionFactory().close(); //session.close();
    }
}