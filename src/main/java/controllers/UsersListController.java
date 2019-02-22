package controllers;

import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
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

@WebServlet("/userslist")
public class UsersListController extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("/pages/user/usersList.jsp");
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute("allusers");
        try {
            List<UserBean> usersList = allUsers.getAllUsers();
            req.setAttribute("usersList", usersList);
            dispatcher.forward(req, resp);
        } catch (DAOSystemException | DAONoSuchEntityException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        //        finally {getServletContext().setAttribute("allusers", allUsers);}
    }
}
