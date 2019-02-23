package controllers;

import dao.exceptions.DAOException;
import dao.user.DAOUser;
import model.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *      Servlet for output all users. It bounds with jsp page(/pages/user/userList.jsp) which show result of
 *  processing. Get attribute @value ConstAttributeNames.ALL_USERS from context. This is interface instance that
 *  help us to communicate with data base. Get all users in list @param usersList, put as request attribute
 *  named @value ConstAttributeNames.USERS_LIST, do forward to jsp. In way of getting exception send error 404
 *  to response.
 */

@WebServlet("/userslist")
public class UsersListController extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("/pages/user/usersList.jsp");
        //
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(ConstAttributeNames.ALL_USERS);
        try {
            List<UserBean> usersList = allUsers.getAllUsers();
            req.setAttribute(ConstAttributeNames.USERS_LIST, usersList);
            dispatcher.forward(req, resp);
        } catch (DAOException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
