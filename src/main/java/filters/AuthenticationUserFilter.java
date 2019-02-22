package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *      AuthenticationUserFilter tracking path /user/* and operations/logout. All sessions that contain attribute name='user'
 *  are approved to visit this path, in other way client will redirected to home page
 */

@WebFilter(filterName = "userFilter",
        urlPatterns = {"/user/*", "/operations/logout"},
        dispatcherTypes = {DispatcherType.REQUEST})
public class AuthenticationUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
