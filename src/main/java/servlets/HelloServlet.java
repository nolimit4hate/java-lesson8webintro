package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet" )
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setBufferSize(9999);
        // or may be use response.getWriter().write("???");
        try(PrintWriter out = response.getWriter()){
            out.println("" +
                    "<html lang='en'>" +
                    "<body>" +
                    "<h1>" +
                    "&lt;h1&gt; " +
                    "Hello Servlet!" +
                    "&lt;/h1&gt;" +
                    "</h1>" +
                    "</body>" +
                    "</html>");
        }
    }
}
