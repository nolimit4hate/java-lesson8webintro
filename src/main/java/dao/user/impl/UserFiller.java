package dao.user.impl;

import model.CountryPool;
import model.UserBean;
import model.UserRolePool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFiller {

    public UserBean getFilledUserFromResultSet(ResultSet resultSet) throws SQLException {
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
}
