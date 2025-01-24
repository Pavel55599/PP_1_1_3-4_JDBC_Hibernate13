package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String SQL_DRIVER =  "com.mysql.cj.jdbc.Driver";   //"com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/schemaex2";
    private static final String USER = "root";
    private static final String PASS = "3937Tyvm";



    public  Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(SQL_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("ПОДКЛЮЧЕНИЕ УСТАНОВЛЕНО");
        } catch (ClassNotFoundException | SQLException e) {
            // e.printStackTrace();
            System.out.println("ПОДКЛЮЧЕНИЕ HE УСТАНОВЛЕНО, ПРОВЕРЬ ВВОДИМЫЕ ДАННЫЕ");
            // throw new RuntimeException(e);
        }
        return connection;
    }
}

