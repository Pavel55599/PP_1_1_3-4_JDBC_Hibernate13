package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

//import java.sql.*;                //отключил вилдкарт
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.sql.Connection;        // Добавил Явный импорт
import java.sql.PreparedStatement; // Добавил Явный импорт
import java.sql.SQLException;      // Добавил Явный импорт
import java.sql.Statement;         // Добавил Явный импорт
import java.sql.ResultSet;         // Добавил Явный импорт



public class UserDaoJDBCImpl extends Util implements UserDao {



    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() throws SQLException {                                 //создание таблицы юзеров
        Connection connection = getConnection();

        Statement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS users (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE INT)";


        try {
            connection.setAutoCommit(false);                      // Отключаем autocommit
            stmt = connection.createStatement();                  //ОСТАЛЬНОЙ КОД НЕ ТРОГАЕМ, ПРОСТО ДОБАВЛЯЕМ НЕСКОЛЬКО СТРОК
            stmt.executeUpdate(sql);                              //конкретно три строки :  connection.setAutoCommit(false); connection.commit();   connection.rollback();
            connection.commit();                                  //явно комитим
        } catch (SQLException e) {
            connection.rollback();                                //откатываем транзакцию в случае ошибки
            throw new RuntimeException(e);                        //ОСТАЛЬНЫЕ МЕТОДЫ СДЕЛАЛ АНАЛОГИЧНО ЭТОМУ, КОММЕНТЫ НЕ ДОБАВЛЯЛ
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void dropUsersTable() throws SQLException {                                 //удалить таблицу юзеров
        Connection connection = getConnection();
        Statement stmt = null;
        String sql = "DROP TABLE IF EXISTS users";

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


    }

    public void saveUser(String name, String lastName, byte age) throws SQLException { // сохранить юзера
        if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Имя и фамилия не могут быть пустыми");
        }

        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void removeUserById(long id) throws SQLException {                           //удалить юзера по ID
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM users WHERE id = ?";

        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public List<User> getAllUsers() throws SQLException {                                 //получить всех пользователей ,список юзеров
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        Statement stmt = null;
        String sql = "SELECT * FROM users";

        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return users;
    }

    public void cleanUsersTable() {                                                       //очистить таблицу пользователей
        Connection connection = getConnection();
        Statement stmt = null;
        String sql = "DELETE FROM users";

        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }
}
