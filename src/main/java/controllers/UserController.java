package controllers;

import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import dao.user.DAOUser;
import model.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserController extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("#Users# start work");
        dispatcher = req.getRequestDispatcher("/pages/user/user.jsp");
        String userName = getUserNameFromPathInfo(req, resp);
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute("allusers");
        try {
            UserBean user = allUsers.getUserByNick(userName);
            req.setAttribute("userToShow", user);
            System.out.println(user.getName() + "~" +
                    user.getEmail() + "~" +
                    user.getPassword());
            dispatcher.forward(req, resp);
        } catch (DAOSystemException | DAONoSuchEntityException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        //        finally {getServletContext().setAttribute("allusers", allUsers);}
    }

    private String getUserNameFromPathInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getPathInfo();
        if(req.getPathInfo() == null || req.getPathInfo().isEmpty() || req.getPathInfo().length() < 3){
            resp.sendRedirect(req.getContextPath() + "/user/" + req.getSession().getAttribute("user"));
            return "";
        } else {
            // deleting first char '/'
            userName = userName.substring(1, userName.length());
            if(userName.contains("/"))
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return userName;
    }

}
