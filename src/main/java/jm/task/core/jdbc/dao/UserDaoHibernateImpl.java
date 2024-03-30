package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `mydbtest`.`users` " +
                    "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(50) NOT NULL," +
                    " `lastName` VARCHAR(50) NOT NULL," +
                    " `age` TINYINT NOT NULL," +
                    " PRIMARY KEY (`id`))").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            allUsers = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return allUsers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
