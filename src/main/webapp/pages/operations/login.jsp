<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="controllers.AttributeName" %>

<html>
<head>
    <c:set scope="page" var="noUser" value="${AttributeName.NO_USER}"></c:set>
    <c:set scope="page" var="registred" value="${AttributeName.NAME_REGISTRED}"></c:set>
    <c:set scope="page" var="userName" value="${AttributeName.POST_USER_NAME}"></c:set>
    <c:set scope="page" var="userPassword" value="${AttributeName.POST_USER_PASSWORD}"></c:set>
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
            <c:if test="${requestScope.get(pageScope.noUser) ne null}">
                <p class="pfield" align="center" style="color:crimson;">Wrong name or password</p>
            </c:if>
            <c:if test="${requestScope.get(pageScope.registred) ne null}">
                <p class="pfield" align="center" style="color:crimson;">
                    User <b>${requestScope.get(pageScope.registred)}</b> was registered
                </p>
            </c:if>
            <p class="pfield">
                <input type="text" class="fields" name="${pageScope.userName}" required="required" placeholder="Nickname">
            </p>
            <p class="pfield">
                <input type="password" class="fields" name="${pageScope.userPassword}" required="required" placeholder="password">
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