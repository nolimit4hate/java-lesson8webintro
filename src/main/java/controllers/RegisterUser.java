package controllers;

import dao.exceptions.DAOException;
import dao.user.DAOUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      this servlet for processing registration logic. we have RequestDispatcher @param dispatcher that bound us
 *  with registration.jsp(real path is /pages/operation/login.jsp) page which is contain registration form with method
 *  'POST'(this form will be sent to this servlet for processing). Also if registratin will be successfully do
 *  redirect to path /operations/login(login servlet with real path controllers.LoginUser)
 *      First we get request with 'get' method. Do forward to registration page.
 *      doPost method is processing 'POST' request. Get request parameters named @value ConstAttributeNames.POST_USER_NAME
 * as user name, @value ConstAttributeNames.POST_USER_PASSWORD as user password, @value ConstAttributeNames.POST_USER_EMAIL
 * as user email. If they are null or empty then forward to jsp with message about wrong entry data, else get context
 * attribute named @value ConstAttributeNames.ALL_USERS for adding user to data base.
 *      If we get success then set session attribute name= @value ConstAttributeNames.NAME_REGISTRED with value= @param userName
 * and do redirect to login servlet(/operations/login),
 * else set request attribute @value ConstAttributeNames.NO_USER and do forward to our jsp page.
 *
 */

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
        // get user name, password, email parameters from request(sending to us in form)
        String userName = req.getParameter(ConstAttributeNames.POST_USER_NAME);
        String userEmail = req.getParameter(ConstAttributeNames.POST_USER_EMAIL);
        String userPass = req.getParameter(ConstAttributeNames.POST_USER_PASSWORD);
        // checking for null or empty
        if(CheckObjects.isStringsNullOrEmpty(userName, userEmail, userPass)){
            dispatcher.forward(req, resp);
        }
        // DAOUser interface help us to communicate with data base
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute(ConstAttributeNames.ALL_USERS);

        try {
            // add user to data base
            allUsers.addUser(userName, userPass, userEmail);
            req.getSession().setAttribute(ConstAttributeNames.NAME_REGISTRED, userName);
            resp.sendRedirect(req.getContextPath() + "/operations/login");
        } catch (DAOException e) {
            req.setAttribute(ConstAttributeNames.NO_USER, ConstAttributeNames.NO_USER);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getRegistrationRequestDispatcher(req);
        dispatcher.forward(req, resp);
    }

}
