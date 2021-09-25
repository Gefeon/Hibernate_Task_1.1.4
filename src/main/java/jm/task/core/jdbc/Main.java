package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("George", "Washington", (byte) 67);
        userService.saveUser("John", "Adams", (byte) 90);
        userService.saveUser("Thomas", "Jefferson", (byte) 83);
        userService.saveUser("James", "Madison", (byte) 85);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
