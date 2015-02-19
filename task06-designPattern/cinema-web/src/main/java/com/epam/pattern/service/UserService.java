package com.epam.pattern.service;

import com.epam.pattern.BusinessException;
import com.epam.pattern.domain.User;
import com.epam.pattern.repository.UserRepository;
import com.epam.pattern.system.DBConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aliaksandr_Shynkevich on 2/7/15
 */
public class UserService extends AbstractService {

    private UserRepository userRepository = new UserRepository();

    public UserService(DBConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<User> getUsers() throws SQLException {
        Connection connection = getConnection();
        try {
            return userRepository.findUsers(connection);
        } finally {
            closeConnection(connection);
        }
    }

    public User getUserByName(String name) throws SQLException {
        Connection connection = getConnection();
        try {
            return userRepository.findUserByName(name, connection);
        } finally {
            closeConnection(connection);
        }
    }

    public User findById(String id) throws BusinessException {
        Connection connection = getConnection();
        try {
            return userRepository.read(id, connection);
        } catch (SQLException e) {
            throw new BusinessException("Find user by id error: ", e);
        } finally {
            closeConnection(connection);
        }
    }

}
