package controllers;

import dao.exceptions.DAONoSuchEntityException;
import dao.exceptions.DAOSystemException;
import dao.user.DAOUser;
import model.UserRolePool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/operations/login")
public class LoginUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RequestDispatcher dispatcher;
    private HttpSession session;

    private void getLoginRequestDispatcher(HttpServletRequest req){
        dispatcher = req.getRequestDispatcher("/pages/operations/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getLoginRequestDispatcher(req);
        session = req.getSession();

        String userName = req.getParameter(AttributeName.POST_USER_NAME);
        String userPassword = req.getParameter(AttributeName.POST_USER_PASSWORD);

        if(CheckObjects.isStringsNullOrEmpty(userName, userPassword)){
            req.setAttribute(AttributeName.EMPTY_FIELDS, "empty");
            dispatcher.forward(req, resp);
        }
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(AttributeName.ALL_USERS);
        try {
            allUsers.isUserExistByNickPass(userName, userPassword);

            session.setAttribute(AttributeName.USER, userName);
            session.setAttribute(AttributeName.USER_ROLE, UserRolePool.USER);
            session.setAttribute(AttributeName.USER_IP, req.getRemoteAddr());

            Cookie loginCookie = new Cookie(AttributeName.USER, userName);
            loginCookie.setMaxAge(30*60);
            resp.addCookie(loginCookie);

            resp.sendRedirect(req.getContextPath());
        } catch (DAOSystemException | DAONoSuchEntityException e) {
            req.setAttribute(AttributeName.NO_USER, AttributeName.NO_USER);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLoginRequestDispatcher(req);
        session = req.getSession();
        String registredUser = (String) session.getAttribute(AttributeName.NAME_REGISTRED);
        if(!CheckObjects.isStringsNullOrEmpty(registredUser)){
            req.setAttribute(AttributeName.NAME_REGISTRED, registredUser);
            session.removeAttribute(AttributeName.NAME_REGISTRED);
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
    }
}
