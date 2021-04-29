package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGINT NOT NULL AUTO_INCREMENT, " +
            "name CHAR(50), " +
            "lastname CHAR(50), " +
            "age TINYINT, " +
            "PRIMARY KEY (id))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private static final String CLEAN_TABLE = "DELETE FROM users";

    private Session session;
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        System.out.println(" User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            users =  session.createQuery("From User").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
