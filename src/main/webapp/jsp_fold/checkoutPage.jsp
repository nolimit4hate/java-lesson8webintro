<%--
  Created by IntelliJ IDEA.
  User: nodota
  Date: 07.02.19
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>Login success page</title>
</head>
<body>
<%
    //allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("../");
    }
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
        }
    }
%>
<h3>Hi <%=userName %>, do the checkout.</h3>
<br>
<form action="/mypr/logoutservlet" method="post">
    <input type="submit" value="logout" >
</form>
</body>
</html>