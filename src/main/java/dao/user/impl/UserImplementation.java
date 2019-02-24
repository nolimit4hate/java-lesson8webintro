package dao.user.impl;

import dao.exceptions.DAOException;
import dao.user.DAOUser;
import model.UserBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  DAO implementation for users. 
 */

public class UserImplementation implements DAOUser {

    /**
     * @value connection is out connection to data base
     */
    
    private final Connection connection;

    // constructor, dont mislay it
    public UserImplementation(Connection connection){
        this.connection = connection;
    }

    /**
     *      method for closing connection to data base
     */

    @Override
    public void closeDBConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        final String SELECT_ALL = "SELECT * FROM Users WHERE id >= ?";
        final String ID_FOR_SELECT_ALL_QUERY = "0";

        QueryingValues queryType = QueryingValues.QUERY_SELECT_USERS;
        return doDBQuery(SELECT_ALL, queryType, ID_FOR_SELECT_ALL_QUERY);
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
        QueryingValues queryType = QueryingValues.QUERY_SELECT_USERS;
        List<UserBean> userBeans = doDBQuery(SELECT_USER_BY_NICK, queryType, name);
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
        QueryingValues queryType = QueryingValues.QUERY_ADD_USER;
        doDBQuery(ADD_USER, queryType, userName, userPassword, userEmail, getCurrentDateTime());
    }

    /**
     *      Just checking for existing this user
     *
     * @param userName
     * @param userPassword
     * @throws DAOException if user dont exist in table "users"
     */

    @Override
    public void isUserExistByNickPass(String userName, String userPassword) throws DAOException {
        String SELECT_USER_BY_NICK_PASS = "SELECT name, password FROM Users where name=? and password=?";
        QueryingValues queryType = QueryingValues.QUERY_IS_USER_EXISTS;
        doDBQuery(SELECT_USER_BY_NICK_PASS, queryType, userName, userPassword);
    }

    /**
     *      Generalized method for do data base querying. 
     * 
     * @param queryString is query to DB
     * @param values is params for prepare statement
     * @return list of user bean @type UserBean
     * @throws DAOException is something goes wrong. more often if query dont pass
     */
    private List<UserBean> doDBQuery(String queryString, QueryingValues queryType, String... values)
            throws DAOException {

        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            pStatement = connection.prepareStatement(queryString);
            // set string or integer values for prepare statement
            for (int i = 0; i < values.length; i++) {
                if (isInteger(values[i])) {
                    pStatement.setInt(i + 1, Integer.parseInt(values[i]));
                } else {
                    pStatement.setString(i + 1, values[i]);
                }
            }
            // check group of queryType, then do PrepareStatement.execute or PrepareStatement.executeQuery
            if(queryType.isInGroup(QueryingValues.Group.EXEQUTE)){
                pStatement.execute();
            } else {
                resultSet = pStatement.executeQuery();
            }
            return getResultFromResultSet(resultSet, queryType);
        } catch (SQLException e){
            e.printStackTrace();
            throw new DAOException("SQLException");
        } finally {
            closePrStatementResultSet(pStatement, resultSet);
        }
    }

    /**
     *      Generalised method of getting needed result. If query type is @value QueryingValues.QUERY_ADD_USER
     *  then return null(because if we did not get exception is mean successfully added user to DB). If query
     *  type is @value QueryingValues.QUERY_IS_USER_EXIST we check @param resultSet for null or empty, if success
     *  then throw DAOException, if not means that user is exists in data base. If query type is @value
     *  @value QueryingValues.QUERY_SELECT_USERS return list of users in @type UserBean.
     *
     * @param resultSet
     * @param queryType
     * @return result which depends on @param queryType.
     * @throws DAOException if enter wrong queryType, or if some of conditions dont pass
     * @throws SQLException anytime trying to do resultSet.next it can be threw
     */

    private List<UserBean> getResultFromResultSet(ResultSet resultSet, QueryingValues queryType)
            throws DAOException, SQLException{

        switch (queryType) {
            // we got SQLException if user already exists, if nothing happens this is success. we dont need
            // list of users in this query
            case QUERY_ADD_USER: { return null; }
            // if resultSet is null or empty then throw DAOException, else do nothing = return null
            case QUERY_IS_USER_EXISTS: {
                if(resultSet != null && resultSet.next()) {
                    return null;
                }
            }
            case QUERY_SELECT_USERS: { return getUsersListFromResultSet(resultSet); }
        }
        throw new DAOException("WRONG QUERY TYPE");
    }

    /**
     *      method for closing statement or prepare statement and result set
     * @param statement
     * @param rSet
     */
    private <T extends Statement>  void closePrStatementResultSet(T statement, ResultSet rSet) {
        try {
            if(statement != null)
                statement.close();
            if(rSet != null)
                rSet.close();
        } catch (SQLException e) {}
    }

    /**
     *      Method for getting list of user beans from ResultSet @param resultSet
     *
     * @param resultSet getting from query to data base
     * @return list of user beans @type UserBean
     * @throws DAOException if user will not found
     * @throws SQLException
     */

    private List<UserBean> getUsersListFromResultSet(ResultSet resultSet) throws DAOException, SQLException{
        if(resultSet != null){
            List<UserBean> usersList = new ArrayList<>();
            for(; resultSet.next();) {
                usersList.add(getFilledUserBean(resultSet));
            }
            return usersList;
        } else throw new DAOException("Wrong name or password");
    }

    /**
     *      Method that get us filled user bean with data which getting from result set
     *
     * @param resultSet
     * @return  user in @type UserBean
     * @throws SQLException  anytime when we try getString from result set it can be threw
     */

    private UserBean getFilledUserBean(ResultSet resultSet) throws SQLException{
       UserFiller userFiller = new UserFiller();
       return userFiller.getFilledUserFromResultSet(resultSet);
    }

    /**
     *      Method for generate current date in @type String in format "yearMonthDay_HoursMinutesSeconds"
     *
     * @return current date-time in @type String
     */

    private String getCurrentDateTime(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    /**
     *      check if string @param string can be integer type. return true if can, false if cant.
     *
     * @param string string that can be integer
     * @return true if @param string can be integer, false if cant.
     */

    private static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
