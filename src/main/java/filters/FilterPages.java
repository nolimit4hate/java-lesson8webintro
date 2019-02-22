package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *      this filter dont approve anyone to visit(request or forward) path /pages/* and send response code 404.
 *
 */

@WebFilter(filterName = "pagesFilter",
        urlPatterns = {"/pages/*"},
        dispatcherTypes = {DispatcherType.REQUEST})
public class FilterPages implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
