package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static appConst.PathConst.*;


public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURI = REDIRECT_TO_LOGIN;

//        String uri = req.getRequestURI();
//        this.context.log("Request Resource::" + uri);
//        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean uriEndsLoggingServlet = req.getRequestURI().endsWith("/loginservlet");
//        if(session == null && !(uri.endsWith("html")) || uri.endsWith("loginservlet")){
//            this.context.log("UN-authorized access request");
//            resp.sendRedirect(REDIRECT_TO_LOGIN);
//        } else {
//            chain.doFilter(request, response);
//        }

        if(loggedIn || loginRequest || uriEndsLoggingServlet){
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
