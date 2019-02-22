package dao.user;

import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import dao.exceptions.DAOEntryAlreadyExistsException;
import model.UserBean;

import java.sql.Connection;
import java.util.List;

public interface DAOUser {

    List<UserBean> getAllUsers() throws DAOSystemException, DAONoSuchEntityException;
    UserBean getUserByNick(String name) throws DAOSystemException, DAONoSuchEntityException;
    void isUserExistByNickPass(String name, String password) throws DAOSystemException, DAONoSuchEntityException;
    void addUser(UserBean user) throws DAOSystemException, DAOEntryAlreadyExistsException;


    void closeDBConnection();
}
