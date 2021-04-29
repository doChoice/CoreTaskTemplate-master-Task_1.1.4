package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCbase?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "psw_pa$$w0Rd";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);

            configuration.setProperty(Environment.DRIVER, DB_DRIVER);
            configuration.setProperty(Environment.URL, URL);
            configuration.setProperty(Environment.USER, LOGIN);
            configuration.setProperty(Environment.PASS, PASSWORD);
            configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty(Environment.SHOW_SQL, "true");
            configuration.setProperty(Environment.HBM2DDL_AUTO, "create");


            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory;
    }
}
