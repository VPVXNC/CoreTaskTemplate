package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private final UserDao userDaoJDBC = UserDaoJDBCImpl.getInstance();
    private final UserDao userDaoHibernate = UserDaoHibernateImpl.getInstance();

    public void createUsersTable() throws SQLException {
        //userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        //userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }
}
