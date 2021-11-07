package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = UserServiceImpl.getInstance();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Popov", Byte.parseByte("25"));
        userService.saveUser("Artem", "Chetverin", Byte.parseByte("25"));
        userService.saveUser("Olga", "Kuznetsova", Byte.parseByte("24"));
        userService.saveUser("Albert", "Bydagyan", Byte.parseByte("23"));
//
//        System.out.println(userService.getAllUsers());

//        userService.removeUserById(1);
//        userService.removeUserById(2);
//        userService.removeUserById(3);
//        userService.removeUserById(4);
//
//        userService.saveUser("Albert", "Bydagyan", Byte.parseByte("23"));

        //          userService.cleanUsersTable();
//
        userService.dropUsersTable();
    }
}