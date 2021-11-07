package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static UserDaoHibernateImpl userDaoHibernateImpl;
    private final SessionFactory sessionFactory;

    private UserDaoHibernateImpl() {
        sessionFactory = Util.HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(UserDaoJDBCImpl.CREATE_TABLE).executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(UserDaoJDBCImpl.DROP_TABLE).executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static UserDaoHibernateImpl getInstance() {
        if (userDaoHibernateImpl == null) {
            userDaoHibernateImpl = new UserDaoHibernateImpl();
        }
        return userDaoHibernateImpl;
    }
}
