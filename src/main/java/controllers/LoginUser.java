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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getLoginRequestDispatcher(req);
        HttpSession session = req.getSession();

        String registredUser = (String) req.getSession().getAttribute("userIsRegistred");
        if(registredUser != null && !registredUser.isEmpty()){
            req.setAttribute("userWasRegistred", registredUser);
            session.removeAttribute("userWasRegistred");
            dispatcher.forward(req, resp);
        }

        String userName = req.getParameter("username");
        String userPassword = req.getParameter("password");
        if(userName == null || userPassword == null || userName.isEmpty() || userPassword.isEmpty()){
            req.setAttribute("fields", "empty");
            dispatcher.forward(req, resp);
        }
        DAOUser allUsers = (DAOUser) getServletContext().getAttribute("allusers");
        try {
            allUsers.isUserExistByNickPass(userName, userPassword);
            session.setAttribute("user", userName);
            session.setAttribute("userrole", UserRolePool.USER);
            session.setAttribute("userip", req.getRemoteAddr());

            Cookie loginCookie = new Cookie("user", userName);
            loginCookie.setMaxAge(30*60);
            resp.addCookie(loginCookie);

            resp.sendRedirect(req.getContextPath());
        } catch (DAOSystemException | DAONoSuchEntityException e) {
            req.setAttribute("nouser", "nouser");
            dispatcher.forward(req, resp);
        }
//        finally {getServletContext().setAttribute("allusers", allUsers);}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getLoginRequestDispatcher(req);
        dispatcher.forward(req, resp);
    }

    private void getLoginRequestDispatcher(HttpServletRequest req){
        dispatcher = req.getRequestDispatcher("/pages/operations/login.jsp");
    }

    private void printContextAttr(){
        Enumeration<String> attributeNames = getServletContext().getAttributeNames();
        System.out.println("#Context Attributes");
        for(; attributeNames.hasMoreElements();){
            String currentName = attributeNames.nextElement();
            System.out.println("# name=" + currentName + "\n# value=" +
                    getServletContext().getAttribute(currentName));
        }
    }
}
