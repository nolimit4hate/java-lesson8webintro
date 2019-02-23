package controllers;

import dao.exceptions.DAOException;
import dao.user.DAOUser;
import model.UserRolePool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 *      this servlet for processing login logic. we have RequestDispatcher @param dispatcher that bound us
 *  with login.jsp(real path is /pages/operation/login.jsp) which is contain login form with method
 *  'POST'(this form will be sent to this servlet for processing) and will show our processing result.
 *      First we get request with 'get' method. Checking for attribute @value ConstAttributeNames.NAME_REGISTRED
 *  in session, if it exists then get value of this attribute, remove it from session, add it to request as
 * @value ConstAttributeNames.NAME_REGISTRED and do forward to jsp. If value of attribute is null, we do
 * forward to jsp.
 *      doPost method is processing 'POST' request. Get request parameters named @value ConstAttributeNames.POST_USER_NAME
 * as user name, @value ConstAttributeNames.POST_USER_PASSWORD as user password. If they are null or empty then
 * forward to jsp with message about wrong entry data, else get context attribute named @value ConstAttributeNames.ALL_USERS
 * as DAOUser object which contains need methods for get information from data base. Then checking user name and
 * password in data base, if we get exception DAOException then forward to jsp with message "wrong name or
 * password", in other way set session attributes with:
 *      a1) name= @value ConstAttributeNames.USER value= @param userName;
 *      a2) name= @value ConstAttributeNames.USER_ROLE value= UserRolePool.USER;
 *      a3) name= @value ConstAttributeNames.USER_IP value= user ip, getting from request.
 * Then set cookie with @param userName to response.
 *      Finally do redirect to home page
 *
 */

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
        // get value of user name and user password
        String userName = req.getParameter(ConstAttributeNames.POST_USER_NAME);
        String userPassword = req.getParameter(ConstAttributeNames.POST_USER_PASSWORD);
        // checking for null or empty
        if(CheckObjects.isStringsNullOrEmpty(userName, userPassword)){
            req.setAttribute(ConstAttributeNames.EMPTY_FIELDS, "empty");
            dispatcher.forward(req, resp);
        }
        // DAOUser interface contains methods data base connection and send query
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(ConstAttributeNames.ALL_USERS);
        try {
            allUsers.isUserExistByNickPass(userName, userPassword);
            // put attributes to session
            session.setAttribute(ConstAttributeNames.USER, userName);
            session.setAttribute(ConstAttributeNames.USER_ROLE, UserRolePool.USER);
            session.setAttribute(ConstAttributeNames.USER_IP, req.getRemoteAddr());
            // add cookie to response
            Cookie loginCookie = new Cookie(ConstAttributeNames.USER, userName);
            loginCookie.setMaxAge(30*60);
            resp.addCookie(loginCookie);
            // redirect to home page
            resp.sendRedirect(req.getContextPath());
        } catch (DAOException e) {
            req.setAttribute(ConstAttributeNames.NO_USER, ConstAttributeNames.NO_USER);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLoginRequestDispatcher(req);
        session = req.getSession();
        String registredUser = (String) session.getAttribute(ConstAttributeNames.NAME_REGISTRED);
        // check for null or empty
        if(!CheckObjects.isStringsNullOrEmpty(registredUser)){
            req.setAttribute(ConstAttributeNames.NAME_REGISTRED, registredUser);
            session.removeAttribute(ConstAttributeNames.NAME_REGISTRED);
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
    }
}
