package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
//    private final UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();     //вот так у меня было
//    private final UserDao userDao = new UserDaoJDBCImpl();             // вот так думал сделать

    private final UserDao userDao;                                       //вот так по итогу сделал

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //ДОБАВИЛ ЛОГИКУ СВЯЗЫВАНИЯ ДВУХ КЛАССОВ
    //ДЛЯ ЭТОГО СОЗДАЛ ЭКЗЕМПЛЯР КЛАССА UserDaoJDBCImpl ВНУТРИ КЛАССА UserServiceImpl
    // И В МЕТОДЫ ЭТОГО КЛАССА ВОТКНУЛ МЕТОДЫ ВНЕШНЕГО КЛАССА, И ВНУТРЕННИЙ ИХ ВЫЗЫВАЕТ
    //ПРИ ЭТОМ НАСЛЕДОВАНИЕ НЕ ИСПОЛЬЗУЕМ
    //  Я ТАК ПОНИМАЮ ЧТО ЭТО И ЕСТЬ МЕТОДЫ МОСТЫ
    public void createUsersTable() throws SQLException {                                //создание таблицы юзеров
        userDao.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {                                  //удалить таблицу юзеров
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {  //сохранить юзера
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws SQLException {                           //удальть юзера по ид
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {                                //получить всех пользователей список юзеров
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {                                                      //очистить таблицу пользователей
        userDao.cleanUsersTable();

    }
}
