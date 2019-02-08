package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import static appConst.PathConst.*;

@WebServlet( description = "Login Servlet", urlPatterns={"/loginservlet"},
             initParams = {
                            @WebInitParam(name = "user", value = "goi"),
                            @WebInitParam(name = "password", value = "123")
             })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String myUserID = "gg";
    private final String myPass = "pswd";
    private final String adminID = "ad";
    private final String adminPassword = "ad";

    @Override
    public void init() throws ServletException {
//        // create DB connection resource here and set it to Servlet context
//        if(getServletContext().getInitParameter("dbUrl").equals("jdbc:mysql://localhost/mysql_db") &&
//                getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
//                getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd")){
//
//            getServletContext().setAttribute("DB_Success", "True");
//        } else throw new ServletException("DB Connection error");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        if(adminID.equals(user) && adminPassword.equals(pwd)){
            HttpSession session = req.getSession();
            session.setAttribute("user", "goi");
            session.setMaxInactiveInterval(1800);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(1800);
            resp.addCookie(userName);
            resp.sendRedirect(REDIRECT_TO_LOGIN_SUCCESS);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(REDIRECT_TO_LOGIN);
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(req, resp);
        }

//        if(myUserID.equals(user) && myPass.equals(pwd)){
//            Cookie loginCookie = new Cookie("user", user);
//            resp.addCookie(loginCookie);
//            resp.sendRedirect("./jsp_fold/loginSuccess.jsp");
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/htmlpart/loginForm.html");
//            PrintWriter out = resp.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(req, resp);
//        }
//
//        // get servlet config init params
//        String userID = getServletConfig().getInitParameter("user");
//        String password = getServletConfig().getInitParameter("password");
//
//        // logging example
//        log("User=" + user + "::password=" + pwd);
//
//        if(userID.equals(user) && password.equals(pwd)){
//            resp.sendRedirect("./jsp_fold/loginSuccess.jsp");
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/htmlpart/loginForm.html");
//            PrintWriter out = resp.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(req, resp);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
