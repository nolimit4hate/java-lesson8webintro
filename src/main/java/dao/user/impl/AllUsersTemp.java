package dao.user.impl;

import model.CountryPool;
import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import dao.exceptions.DAOEntryAlreadyExistsException;
import dao.user.DAOUser;
import model.UserBean;
import model.UserRolePool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AllUsersTemp implements DAOUser {
    private Connection connection;

    public AllUsersTemp(Connection connection){
        this.connection = connection;
    }

    @Override
    public void closeDBConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserBean> getAllUsers() throws DAOSystemException, DAONoSuchEntityException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users");

            if(resultSet != null){
                List<UserBean> result = new ArrayList<>();
                for(; resultSet.next();) {
                    UserBean newUser = new UserBean();
                    newUser.setName(resultSet.getString("name"));
                    newUser.setEmail(resultSet.getString("email"));
                    newUser.setPassword(resultSet.getString("password"));
                    newUser.setFio(resultSet.getString("fio"));
                    newUser.setCrdate(resultSet.getString("crdate"));
                    String country = resultSet.getString("country");
                    if (country == null)
                        newUser.setCountry(null);
                    else if (country.equals("ukraine"))
                        newUser.setCountry(CountryPool.UKRAINE);
                    else newUser.setCountry(CountryPool.OTHER);
                    String userRole = resultSet.getString("userRole");
                    newUser.setUserRolePool(UserRolePool.USER);
                    result.add(newUser);
                }
                return result;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAONoSuchEntityException("ERROR: NO USERS");
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e){}
        }
        throw new DAONoSuchEntityException("ERROR: NO USERS");
    }

    @Override
    public UserBean getUserByNick(String name) throws DAOSystemException, DAONoSuchEntityException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(
            "SELECT * FROM Users WHERE name=?"
            );
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null && resultSet.next()){
                UserBean result = new UserBean();
                result.setName(resultSet.getString("name"));
                result.setEmail(resultSet.getString("email"));
                result.setPassword(resultSet.getString("password"));
                result.setFio(resultSet.getString("fio"));
                result.setCrdate(resultSet.getString("crdate"));
                String country = resultSet.getString("country");
                if(country == null)
                    result.setCountry(null);
                else
                    if(country.equals("ukraine"))
                    result.setCountry(CountryPool.UKRAINE);
                    else result.setCountry(CountryPool.OTHER);
                String userRole = resultSet.getString("userRole");
                result.setUserRolePool(UserRolePool.USER);
                return result;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAONoSuchEntityException("No user in DB");
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e){}
        }
        throw new DAONoSuchEntityException("ERROR: NO USER WITH NICK={" + name + "}");
    }

    @Override
    public void addUser(UserBean user) throws DAOSystemException, DAOEntryAlreadyExistsException {
        PreparedStatement prStatement = null;
        try{
            prStatement = connection.prepareStatement(
            "INSERT INTO users (name, email, crdate, password) values(?, ?, ?, ?)"
            );
            prStatement.setString(1, user.getName());
            prStatement.setString(2, user.getEmail());
            prStatement.setString(3, getCurrentDate());
            prStatement.setString(4, user.getPassword());
            prStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAOEntryAlreadyExistsException("User already exist");
        } finally {
            try {
                prStatement.close();
            } catch (SQLException e) {}
        }
    }

    @Override
    public void isUserExistByNickPass(String name, String password) throws DAOSystemException, DAONoSuchEntityException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try {
            prStatement = connection.prepareStatement(
                    "SELECT name, password FROM Users where name=? and password=?");
            prStatement.setString(1, name);
            prStatement.setString(2, password);
            resultSet = prStatement.executeQuery();
            if(resultSet != null && resultSet.next()){
                return;
            } else throw new DAONoSuchEntityException("Wrong name or password");

        } catch (SQLException e){
            e.printStackTrace();
            throw  new DAONoSuchEntityException("SQLException");
        } finally {
            try {
                prStatement.close();
                resultSet.close();
            } catch (SQLException e){}
        }
    }

    private String getCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
}
