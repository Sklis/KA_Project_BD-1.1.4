package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mydbtest?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
    private static final String username = "sklis";
    private static final String password = "1359";
    private static final String dialect = "org.hibernate.dialect.MySQL8Dialect";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        return connection = DriverManager.getConnection(url, username, password);
    }

    public static SessionFactory getSessionFactory() throws Exception {
        Properties setting = new Properties();
        setting.put(Environment.DRIVER, driver);
        setting.put(Environment.URL, url);
        setting.put(Environment.USER, username);
        setting.put(Environment.PASS, password);
        setting.put(Environment.DIALECT, dialect);
        setting.put(Environment.SHOW_SQL, "true");
        setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        setting.put(Environment.HBM2DDL_AUTO, "");

        return new Configuration().addAnnotatedClass(User.class).addProperties(setting).buildSessionFactory();
    }
}
