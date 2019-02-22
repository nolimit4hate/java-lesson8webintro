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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getRegistrationRequestDispatcher(req);

        String userName = req.getParameter("username");
        String userEmail = req.getParameter("email");
        String userPass = req.getParameter("password");

        if(userName == null || userEmail == null || userPass == null ||
           userName.isEmpty() || userEmail.isEmpty() || userPass.isEmpty()){
            dispatcher.forward(req, resp);
        }

        DAOUser allUsers = (DAOUser) getServletContext().getAttribute("allusers");

        UserBean newUser = new UserBean();
        newUser.setName(userName);
        newUser.setEmail(userEmail);
        newUser.setPassword(userPass);
        newUser.setUserRolePool(UserRolePool.USER);

        try {
            allUsers.addUser(newUser);
            req.getSession().setAttribute("userWasRegistred", userName);
            resp.sendRedirect(req.getContextPath() + "/operations/login");
        } catch (DAOSystemException | DAOEntryAlreadyExistsException e) {
            req.setAttribute("nouser", "userexists");
            dispatcher.forward(req, resp);
        }
        //        finally {getServletContext().setAttribute("allusers", allUsers);}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getRegistrationRequestDispatcher(req);
        dispatcher.forward(req, resp);
    }

    private void getRegistrationRequestDispatcher(HttpServletRequest req){
        dispatcher = req.getRequestDispatcher("/pages/operations/registration.jsp");
    }
}
