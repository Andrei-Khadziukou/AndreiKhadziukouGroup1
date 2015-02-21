package com.epam.pattern.repository;

import com.epam.pattern.core.domain.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Shynkevich on 2/7/15
 */
public class UserRepository extends AbstractRepository<User> {

    private static final String READ_SQL = "SELECT * FROM users WHERE user_id <> '0'";
    private static final String READ_BY_NAME_SQL = "SELECT * FROM users WHERE user_name = ?";
    private static final String UPDATE_BALANCE = "UPDATE users SET balance = ? WHERE user_id = ?";

    public List<User> findUsers(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement(READ_SQL).executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(getUser(resultSet));
        }
        return users;
    }

    public User findUserByName(String name, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(READ_BY_NAME_SQL);
        statement.setString(1,name);
        ResultSet resultSet = statement.executeQuery();
        return wrapObject(resultSet);
    }

    public void updateBalance(User user, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);
        statement.setBigDecimal(1, user.getBalance());
        statement.setString(2, user.getId());
        statement.executeUpdate();
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected String getIdColumnName() {
        return "user_id";
    }

    @Override
    protected PreparedStatement getCreateStatement(User entity, Connection connection) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getUpdateStatement(User entity, Connection connection) throws SQLException {
        return null;
    }

    @Override
    protected User wrapObject(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(getIdColumnName());
        String name = resultSet.getString("user_name");
        BigDecimal balance = resultSet.getBigDecimal("balance");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBalance(balance);
        return user;
    }
}
