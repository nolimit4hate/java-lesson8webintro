package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static appConst.PathConst.REDIRECT_TO_LOGIN;

@WebServlet( description = "Logout servlet", urlPatterns = {"/logoutservlet"})
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    System.out.println("JSESSIONID=" + cookie.getValue());
                    break;
                }
            }
        }
        // invalidate session if exists
        HttpSession session = req.getSession(false);
        if(session != null){
            System.out.println("User=" + session.getAttribute("user"));
            session.invalidate();
        }
        resp.sendRedirect(REDIRECT_TO_LOGIN);
    }
}
