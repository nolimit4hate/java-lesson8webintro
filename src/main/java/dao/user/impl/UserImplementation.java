package dao.user.impl;

import dao.exceptions.DAOException;
import model.CountryPool;
import dao.user.DAOUser;
import model.UserBean;
import model.UserRolePool;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  DAO implementation for users. 
 */

public class UserImplementation implements DAOUser {

    /**
     * @param connection is out connection to data base
     */
    
    private final Connection connection;

    // constructor, dont mislay it
    public UserImplementation(Connection connection){
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

    /**
     *      Generalized method for do data base querying. 
     * 
     * @param queryString is query to DB
     * @param values is params for prepare statement
     * @return list of user bean @type UserBean
     * @throws DAOException is something goes wrong. more often if query dont pass
     */
    private List<UserBean> doDBQuery(String queryString, String... values) throws DAOException {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
                pStatement = connection.prepareStatement(queryString);
                // set strings for prepare statement
                // 1st (values[0]) value is name
                // 2st (values[1]) value is password
                // 3st (values[2]) value is email
                // 4st (values[3]) value is creation date
                for (int i = 0; i < values.length; i++) {
                    if(values[0].equals("0"))
                        pStatement.setInt(i + 1, 0);
                    else
                        pStatement.setString(i + 1, values[i]);
                }
                //addUser() query. we need only execute.
                if (values.length == 4) {
                    pStatement.execute();
                    return null;
                } else {
                    // isUserByNickPass
                    // getUserByNick
                    // getAllUsers
                    resultSet = pStatement.executeQuery();
                    return getUsersList(resultSet, values);
                }
        // block of catching exception
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAOException("SQLException");
        } finally {
            try {
                // close statement and result set
                if(pStatement != null)
                    pStatement.close();
                if(resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {}
        }
    }

    /**
     *      Method for getting list of user beans from @param resultSet
     *
     * @param resultSet getting from query to data base
     * @param values
     * @return list of user beans @type UserBean
     * @throws DAOException if user will not found
     * @throws SQLException
     */

    private List<UserBean> getUsersList(ResultSet resultSet, String[] values) throws DAOException, SQLException{
        if(resultSet != null){
            List<UserBean> usersList = new ArrayList<>();
            for(; resultSet.next();) {
                // is user exist query
                // if user exist return null, other way = throw DAOException
                if(values.length == 2)
                    return null;
                // getUserByNick or getAllUsers query
                usersList.add(getFilledUserBean(resultSet));
            }
            return usersList;
        } else throw new DAOException("Wrong name or password");
    }

    /**
     *      Method that get us filled user bean with data which getting from result set
     *
     * @param resultSet
     * @return
     * @throws SQLException  anytime when we try getString from result set it can be threw
     */

    private UserBean getFilledUserBean(ResultSet resultSet) throws SQLException{
        UserBean newUser = new UserBean();
        // filling fields
        newUser.setName(resultSet.getString("name"));
        newUser.setEmail(resultSet.getString("email"));
        newUser.setPassword(resultSet.getString("password"));
        newUser.setFio(resultSet.getString("fio"));
        newUser.setCrDate(resultSet.getString("crdate"));
        String country = resultSet.getString("country");
        // logic to set country
        if (country == null)
            newUser.setCountry(null);
        else if (country.equals("ukraine"))
            newUser.setCountry(CountryPool.UKRAINE);
        else newUser.setCountry(CountryPool.OTHER);
        // lazy logic to set user role
        newUser.setUserRolePool(UserRolePool.USER);
        return newUser;
    }

    /**
     *      Get all users from data base table "users" as list of user beans.
     *
     * @return list of @type UserBean
     * @throws DAOException if table of users is empty
     */
    
    @Override
    public List<UserBean> getAllUsers() throws DAOException {
        // this is some logic query. this shit was made for generalize process method
        String SELECT_ALL = "SELECT * FROM Users WHERE id >= ?";
        return doDBQuery(SELECT_ALL, "0");
    }

    /**
     *      Get user with name= @param name from data base table "users" as user bean.
     *
     * @param name is user name
     * @return object @type UserBean
     * @throws DAOException if user dont exists in table "users"
     */

    @Override
    public UserBean getUserByNick(String name) throws DAOException {
        String SELECT_USER_BY_NICK = "SELECT * FROM Users WHERE name=?";
        List<UserBean> userBeans = doDBQuery(SELECT_USER_BY_NICK, name);
//        if(userBeans)
        return userBeans.get(0);
    }

    /**
     *      Add user to data base table "users".
     *
     * @param userName
     * @param userPassword
     * @param userEmail
     * @throws DAOException if user with current parameters already exists
     */

    @Override
    public void addUser(String userName, String userPassword, String userEmail) throws DAOException {
        String ADD_USER = "INSERT INTO users (name, password, email, crdate) values(?, ?, ?, ?)";
        doDBQuery(ADD_USER, userName, userPassword, userEmail, getCurrentDateTime());
    }

    /**
     *      Just checking for existing this user
     *
     * @param name
     * @param password
     * @throws DAOException if user dont exist in table "users"
     */

    @Override
    public void isUserExistByNickPass(String name, String password) throws DAOException {
        String SELECT_USER_BY_NICK_PASS = "SELECT name, password FROM Users where name=? and password=?";
        doDBQuery(SELECT_USER_BY_NICK_PASS, name, password);
    }

    /**
     *      Method for generate current date in @type String in format "yyyyMMdd_HHmmss"
     *
     * @return current date-time
     */

    private String getCurrentDateTime(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
}
