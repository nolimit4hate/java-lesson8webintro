package dao.user;

import dao.exceptions.DAOException;
import model.UserBean;
import java.util.List;

/**
 *      This interface help us to divide DAO logic from business logic
 */

public interface DAOUser {
    // querying
    List<UserBean> getAllUsers() throws DAOException;
    UserBean getUserByNick(String name) throws DAOException;
    void isUserExistByNickPass(String name, String password) throws DAOException;
    void addUser(String userName, String userPassword, String userEmail) throws DAOException;
    // close DB connection
    void closeDBConnection();
}
