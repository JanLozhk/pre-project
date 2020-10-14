package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    UserServiceImpl UserImpl = new UserServiceImpl();
    UserImpl.createUsersTable(); //создать таб.
    UserImpl.saveUser("Dao", "LastDay", (byte) 10);
    UserImpl.saveUser("Lobachevsky", "Mathematic", (byte) 6);
    UserImpl.saveUser("PeaceMaker", "World", (byte) 8);
    UserImpl.saveUser("Java", "FavoriteJava", (byte) 1);

    System.out.println(UserImpl.getAllUsers());// output users
    UserImpl.cleanUsersTable(); // clean
    UserImpl.dropUsersTable(); //del.
    }
}
