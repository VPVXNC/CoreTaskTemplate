package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Cherepitsa", Byte.parseByte("25"));
        userService.saveUser("Artem", "Chetvernin", Byte.parseByte("25"));
        userService.saveUser("Olga", "Kolobkova", Byte.parseByte("24"));
        userService.saveUser("Alber", "Albertov", Byte.parseByte("23"));

        System.out.println(userService.getAllUsers());

        userService.dropUsersTable();
    }
}