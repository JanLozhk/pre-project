package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;

import java.sql.Connection;
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
            Transaction tr = null;
            Session session = null;
            try {
                session = Util.getSessionFactory().openSession();
                session.beginTransaction();
/*                System.out.println(user.getId());
                session.persist(user);
                System.out.println(user.getId());*/
                session.save(user);
                System.out.println(user.getId());
                session.getTransaction().commit();
 //               throw new HibernateException("генерируем искл.save");

            }catch (HibernateException e) {
                e.getStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.getSessionFactory().close();

            }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tr = null;
        Session session = null;
//        User user = new User();// session.get(User.class, id);
        try {
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tr.commit();
        } catch (Exception e) {
            if (tr!=null) {
                tr.rollback();
            }
            e.getStackTrace();
        } finally {
            session.getSessionFactory().close();
        }
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
        Session session = null;
//        System.out.println(Transaction.isActive());
        Transaction tr = null;
        System.out.println((Object) null);
        try {
            session = Util.getSessionFactory().openSession();
            tr = session.beginTransaction();
            System.out.println(tr.isActive());
//            NativeQuery query = session.createSQLQuery(sqlQuery); //executeUpdate();
//            query.executeUpdate();
            session.createSQLQuery(sqlQuery).executeUpdate(); //executeUpdate();
 //           throw new HibernateException("генерируем искл.");
 //           System.out.println(tr.isActive());
            tr.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.getSessionFactory().close();
        }
    }
}