<%--
  Created by IntelliJ IDEA.
  User: nodota
  Date: 06.02.19
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Success Page</title>
</head>
<body>
<%
    // allow access only if session exists
    String user = (String) session.getAttribute("user");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("user"))
            userName = cookie.getValue();
        if(cookie.getName().equals("JSESSIONID"))
            sessionID = cookie.getValue();
    }

%>

<h2>Hi <%=userName%> with <br> session id <%=sessionID%>, <br> Login successful.</h2>
<br>
User = <%=user%>
<br>
<a href="/mypr/jsp_fold/checkoutPage.jsp">Checkout page</a>
<form action="/mypr/logoutservlet" method="post">
    <input type="submit" value="logout">
</form>
</body>
</html>
