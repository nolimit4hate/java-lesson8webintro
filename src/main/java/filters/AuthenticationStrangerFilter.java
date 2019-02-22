package filters;

import controllers.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *      this filter tracking path /operations/login and /operations/registration.
 *  if in your session attribute name = 'user' is null you will redirected to home page
 *  in other way you will be approved to visit this path
 */

@WebFilter(filterName = "strangerFilter",
        urlPatterns = {"/operations/login", "/operations/registration"},
        dispatcherTypes = {DispatcherType.REQUEST})
public class AuthenticationStrangerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if(session.getAttribute(AttributeName.USER) == null){
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
