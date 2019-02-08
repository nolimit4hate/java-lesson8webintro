package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(value = "/myservlet1")
public class MyServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setBufferSize(8000);
        try(PrintWriter out = resp.getWriter()){
            String userName = req.getParameter("userName");
            String userF = req.getParameter("userF");
            out.println("<html> <body>");
            out.println("THIS IS myservlet:");
            //some test
//            out.println("<p>" + getServletContext().getRealPath(req.getServletPath()).toString() + "</p>");
//            out.println("<p>" + req.getServletPath().toString() + "</p>");
//            out.println("<p>" + getServletContext().getServerInfo() + "</p>");
//            out.println("<p>remoteAddr" + req.getRemoteAddr().toString() + "</p>");
//            out.println("<p>remotePort" + req.getRemotePort() + "</p>");
//            System.out.println(req.getRemotePort());
//            System.out.println(req.getRemoteHost());
//            System.out.println(req.getRemoteUser());
//            System.out.println(req.getRemoteAddr());

            //req Dispatcher
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp_fold/helloJsp.jsp");
//            dispatcher.forward(req, resp);


            //context
         /*   out.println("<p>-----</p>");
            out.println("<p>CONTEXT HERE:</p>");
            ServletContext context = req.getServletContext();
            context.setAttribute("name1", "value1");
            Enumeration contextNames = context.getAttributeNames();
            while(contextNames.hasMoreElements()){
                String name = contextNames.nextElement().toString();
                System.out.println("<p>CONTEXT NAMES " + name + "</p>");
            }
            String contextMe = req.getServletContext().toString();
            System.out.println("<p>" + contextMe + "</p>");
*/

            //session
//            HttpSession session = req.getSession(true);
//            Date created = new Date(session.getCreationTime());
//            Date accessed = new Date(session.getLastAccessedTime());
//            out.println("<p>-----</p>");
//            out.println("<p>SESSION HERE:</p>");
//            out.println("<p>SESSION ID: " + session.getId() + "</p>");
//            out.println("<p>SESSION TIME CR " + created + "</p>");
//            out.println("<p>SESSION TIME LAST ACCESS " + accessed + "</p>");
//            session.setAttribute("nameSession", "valueSession");
//            session.setAttribute("name1", "value1");
//
//
//            Enumeration e = session.getAttributeNames();
//            while (e.hasMoreElements()) {
//                String name = (String)e.nextElement();
//                String value = session.getAttribute(name).toString();
//                out.println("<p>" + name + " = " + value + "</p>");
//            }
//            String valueMe = session.getAttribute("name1").toString();
//            out.println("<p>" + valueMe + "</p>");
//
//            //cookie
//            out.println("<p>-----</p>");
//            out.println("<p>COOKIE HERE:</p>");
//
//            Cookie[] cookies = req.getCookies();
//            for(int i = 0; i < cookies.length ; i++){
//                Cookie currentCookie = cookies[i];
//                String cookieName = currentCookie.getName();
//                String cookieValue = currentCookie.getValue();
//
//                out.println("<p>Cookie Name = " + cookieName + "</p>");
//                out.println("<p>Cookie value = " + cookieValue + "</p>");
//                out.println("-----<br>");
//            }
//            resp.addCookie(new Cookie("newCookie", "valueNewCookie"));



            // headers
  /*          Enumeration enumHeaderNames = req.getHeaderNames();
            out.println("<form name='form' method='get'>");
            out.println("<p><input type='text' name='userName'></p>");
            out.println("<p><input type='text' name='userF'></p>");
            out.println("<p><input type='submit' name='userSub'></p>");
            out.println("</form>");
            out.println("<p>userName is " + userName + " | userF is " + userF + "</p>");

            while (enumHeaderNames.hasMoreElements()){
                String headerName = (String)enumHeaderNames.nextElement();
                String headerValue = req.getHeader(headerName);
                out.println("<p>HEADER_NAME: " + headerName + "<br>HEADER VALUE: " + headerValue + "</p>");
            }
            // request info
            out.println("-----");
            out.println("<p>REQUEST INFO</p>");
            out.println("<p>REQUEST method: " + req.getMethod() + "</p>");
            out.println("<p>REQUEST URI: " + req.getRequestURI() + "</p>");
            out.println("<p>REQUEST Protocol: " + req.getProtocol() + "</p>");
            out.println("<p>REQUEST PathInfo: " + req.getPathInfo() + "</p>");
            out.println("<p>REQUEST Remote adress: " + req.getRemoteAddr() + "</p>");
            out.println("<p>REQUEST Remote port: " + req.getRemotePort() + "</p>");
            out.println("<p>REQUEST INFO</p><br>-----<br>");
*/
            out.println("</body></html>");
        }


    }
}
