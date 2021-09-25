package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE if NOT EXISTS Users (" +
                    "ID int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name varchar(45) NOT NULL," +
                    "lastName varchar(45)," +
                    "age int)");
            System.out.println("Table created");
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE if EXISTS Users");
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users VALUES (default,?,?,?)")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " added to database");
            connection.commit();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " deleted");
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                users.add(user);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE Users");
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }
}
