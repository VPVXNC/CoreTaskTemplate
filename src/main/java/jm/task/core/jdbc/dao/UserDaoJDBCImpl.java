package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE users(" +
            "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(64) NOT NULL, " +
            "lastName VARCHAR(64) NOT NULL, " +
            "age TINYINT NOT NULL);";

    private static final String DROP_TABLE = "DROP TABLE users;";

    private static final String INSERT_INTO = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?);";

    private static final String REMOVE_BY_ID = "DELETE FROM users WHERE id = ?;";

    private static final String GET_ALL = "SELECT id," +
            "name," +
            "lastName, " +
            "age" +
            " FROM users;";

    private static final String CLEAR_TABLE = "DELETE FROM users;";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("name"),
                resultSet.getString("lastName"),
                resultSet.getByte("age"));
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_TABLE)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
