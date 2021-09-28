package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Джордж", "Вашингтон", (byte) 67);
        userService.saveUser("Джон", "Адамс", (byte) 90);
        userService.saveUser("Томас", "Джеферсон", (byte) 83);
        userService.saveUser("Джеймс", "Мэдисон", (byte) 85);

        userService.removeUserById(3);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
