package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws SQLException;                                   //создание таблицы юзеров

    void dropUsersTable() throws SQLException;                                     //удалить таблицу юзеров

    void saveUser(String name, String lastName, byte age) throws SQLException;     //созранить юзера

    void removeUserById(long id) throws SQLException;                              //удальть юзера по ид

    List<User> getAllUsers() throws SQLException;                                  //получить всех пользователей список юзеров

    void cleanUsersTable();                                                         //очистить таблицу пользователей
}
