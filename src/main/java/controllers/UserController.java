package controllers;

import dao.exceptions.DAOException;
import dao.user.DAOUser;
import model.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      this servlet for output result about user. servlet bounds with jsp page(/pages/user/user.jsp) that
 * show result communication with data base.
 *      UserController servlet url-pattern={"/user/*"}. All that goes after /user and before request query
 * (I mean query is "?blabla=132") in url is extra path.
 *      We get extra path from request, then delete first symbol(that is '/') and then search in data base
 * user with name = extra path.
 *      If user will be found then output information with forward to jsp, but before it put filled user bean to request
 * attribute named @value ConstAttributeNames.SHOW_USER.
 *      If we got exception or have wrong extra path we send error 404 to response.
 *
 */

public class UserController extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("#Users# start work");
        dispatcher = req.getRequestDispatcher("/pages/user/user.jsp");
        String userName = getUserNameFromPathInfo(req, resp);
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(ConstAttributeNames.ALL_USERS);
        try {
            UserBean user = allUsers.getUserByNick(userName);
            req.setAttribute(ConstAttributeNames.SHOW_USER, user);
            dispatcher.forward(req, resp);
        } catch (DAOException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String getUserNameFromPathInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // pathInfo is extra path
        String userName = req.getPathInfo();
        // check for null or empty or name length less then 2(because first symbol is '/')
        if(CheckObjects.isStringsNullOrEmpty(userName) || req.getPathInfo().length() < 3){
            resp.sendRedirect(req.getContextPath() + "/user/" + req.getSession().getAttribute(ConstAttributeNames.USER));
            return "";
        } else {
            // deleting first char '/'
            userName = userName.substring(1);
            if(userName.contains("/"))
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return userName;
    }

}
