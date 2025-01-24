package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getConnection();


        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();                                                 //создание таблицы юзеров

        userDao.saveUser("ИВАН", "ВЕТРОВ", (byte) 20);               //добавление юзеров
        userDao.saveUser("ФЕДОР", "ЕМЕЛЬЯНЕНКО", (byte) 25);
        userDao.saveUser("ПАВЕЛ", "СИДОРОВ", (byte) 31);
        userDao.saveUser("АЛЕКСЕЙ", "НИКОЛАЕВ", (byte) 38);


        userDao.getAllUsers();                                                       //получение юзеров из БД

        userDao.cleanUsersTable();                                                   //очистка таблицы юзеров

        userDao.dropUsersTable();                                                    // удаление таблицы

    }
}

//1. Пакеты .mvn/wrapper .idea и target не должны попадать в коммит, добавь в gitignore и удали из коммита  ---- СДЕЛАЛ
//2. class UserServiceImpl
//private final UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
//Наследуемся/инжектим, только самые общие классы/интерфейсы                                                ---- ВРОДЕ СДЕЛАЛ
//3.  import java.sql.*; отключи вайлдкард в импортах                                                       ---- СДЕЛАЛ
//4. Необходимо сделать рабочие транзакции самостоятельно, отключить autocommit.                            ---- СДЕЛАЛ