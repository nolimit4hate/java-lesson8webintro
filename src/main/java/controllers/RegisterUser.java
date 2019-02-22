package controllers;

import dao.exceptions.DAOEntryAlreadyExistsException;
import dao.exceptions.DAOSystemException;
import dao.user.DAOUser;
import model.UserBean;
import model.UserRolePool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/operations/registration")
public class RegisterUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RequestDispatcher dispatcher;

    private void getRegistrationRequestDispatcher(HttpServletRequest req){
        dispatcher = req.getRequestDispatcher("/pages/operations/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getRegistrationRequestDispatcher(req);

        String userName = req.getParameter(AttributeName.POST_USER_NAME);
        String userEmail = req.getParameter(AttributeName.POST_USER_EMAIL);
        String userPass = req.getParameter(AttributeName.POST_USER_PASSWORD);

        if(CheckObjects.isStringsNullOrEmpty(userName, userEmail, userPass)){
            dispatcher.forward(req, resp);
        }

        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(AttributeName.ALL_USERS);

        UserBean newUser = new UserBean();
        newUser.setName(userName);
        newUser.setEmail(userEmail);
        newUser.setPassword(userPass);
        newUser.setUserRolePool(UserRolePool.USER);

        try {
            allUsers.addUser(newUser);
            req.getSession().setAttribute(AttributeName.NAME_REGISTRED, userName);
            resp.sendRedirect(req.getContextPath() + "/operations/login");
        } catch (DAOSystemException | DAOEntryAlreadyExistsException e) {
            req.setAttribute(AttributeName.NO_USER, AttributeName.NO_USER);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getRegistrationRequestDispatcher(req);
        dispatcher.forward(req, resp);
    }

}
