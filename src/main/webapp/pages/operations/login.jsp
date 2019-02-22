<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>login</title>
    <style type="text/css">
        <jsp:include page="/pages/css/base.css"></jsp:include>
        <jsp:include page="/pages/css/signinForm.css"></jsp:include>
    </style>
</head>
<body>
<div class="parent">
    <div class="block blockLogin">
        <form class = "mainForm" name="regForm" method="post">
            <p class="formName"><h2 align="center">Login</h2></p>
            <c:if test="${not empty requestScope.nouser}">
                <p class="pfield" align="center" style="color:crimson;">Wrong name or password</p>
            </c:if>
            <c:if test="${not empty requestScope.userWasRegistred}">
                <p class="pfield" align="center" style="color:crimson;">User <b>${requestScope.userWasRegistred}</b> was registered</p>
            </c:if>
            <p class="pfield">
                <input type="text" class="fields" name="username" required="required" placeholder="Nickname">
            </p>
            <p class="pfield">
                <input type="password" class="fields" name="password" required="required" placeholder="password">
            </p>
            <p class="pbutton"><input type="submit" class="button buttonLogin" value="enter"></p>
            <p class="pbutton"><input type="button" class="button buttonLogin" value="goto registration"
                                      onclick='location.href="${pageContext.request.contextPath}/operations/registration"'>
            </p>
        </form>
    </div>
</div>
</body>
</html>