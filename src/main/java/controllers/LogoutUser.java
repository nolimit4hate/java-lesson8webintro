package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 *      Class for do logout of users. typically: set max age of cookies with name @value ConstAttributeNames.JSESSIONID and
 * @value ConstAttributeNames.USER to 0; do session.invalidate method.
 *      Finally redirect to home page.
 */

@WebServlet("/operations/logout")
public class LogoutUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(ConstAttributeNames.JSESSIONID) ||
                          cookie.getName().equals(ConstAttributeNames.USER)){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }

        //invalidate the session if exists
        HttpSession session = req.getSession(false);
        System.out.println("#Logout# User="+session.getAttribute(ConstAttributeNames.USER));
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath());
    }
}
