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
    private static final String URL = "jdbc:mysql://localhost:3306/schemaex2";
    private static final String USER = "root";
    private static final String PASSWORD = "3937Tyvm";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("ПОДКЛЮЧЕНИЕ УСТАНОВЛЕНО");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ПОДКЛЮЧЕНИЕ НЕ УСТАНОВЛЕНО");
            throw new RuntimeException(e);
        }
        return connection;

    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println(" ПОДКЛЮЧЕНИЕ УСТАНОВЛЕНО Б БЛОКЕ ДЛЯ ХИБЕРНЕЙ");
            } catch (Exception e) {
                System.out.println(" ПОДКЛЮЧЕНИЕ НЕЕЕЕЕЕЕЕЕЕ УСТАНОВЛЕНО Б БЛОКЕ ДЛЯ ХИБЕРНЕЙ");
                e.printStackTrace();

            }
        }
        return sessionFactory;
    }
}
//package jm.task.core.jdbc.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Util {
//
//    private static final String SQL_DRIVER =  "com.mysql.cj.jdbc.Driver";   //"com.mysql.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/schemaex2";
//    private static final String USER = "root";
//    private static final String PASS = "3937Tyvm";
//
//
//
//    public  Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            Class.forName(SQL_DRIVER);
//            connection = DriverManager.getConnection(DB_URL,USER,PASS);
//            System.out.println("ПОДКЛЮЧЕНИЕ УСТАНОВЛЕНО");
//        } catch (ClassNotFoundException | SQLException e) {
//            // e.printStackTrace();
//            System.out.println("ПОДКЛЮЧЕНИЕ HE УСТАНОВЛЕНО, ПРОВЕРЬ ВВОДИМЫЕ ДАННЫЕ");
//            // throw new RuntimeException(e);
//        }
//        return connection;
//    }
//}


