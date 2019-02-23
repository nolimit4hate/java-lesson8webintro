<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="controllers.ConstAttributeNames" %>

<html>
<head>
    <c:set scope="page" var="noUser" value="${AttributeName.NO_USER}"></c:set>
    <c:set scope="page" var="userName" value="${AttributeName.POST_USER_NAME}"></c:set>
    <c:set scope="page" var="userEmail" value="${AttributeName.POST_USER_EMAIL}"></c:set>
    <c:set scope="page" var="userPassword" value="${AttributeName.POST_USER_PASSWORD}"></c:set>
    <title>login</title>
    <style type="text/css">
    <jsp:include page="/pages/css/base.css"></jsp:include>
    <jsp:include page="/pages/css/registerForm.css"></jsp:include>
    </style>
</head>
<body>
<div class="parent">
    <div class="block blockRegister">
        <form class = "mainForm" name="regForm" method="post" action="">
            <p class="formName"><h2 align="center">Registration form</h2></p>
            <c:if test="${not empty requestScope.get(pageScope.noUser)}">
                <p class="pfield" align="center" style="color:black;">User or email already exists</p>
            </c:if>
            <p class="pfield">
                <input type="text" class="fields" name="${pageScope.userName}" required="required" placeholder="Nickname">
            </p>
            <p class="pfield">
                <input type="email" class="fields" name="${pageScope.userEmail}" required="required" placeholder="email">
            </p>
            </p class="pfield">
            <input type="password" class="fields" name="${pageScope.userPassword}" required="required" placeholder="password">
            </p>
            <p class="pbutton"><input type="submit" class="button buttonRegister" value="submit"></p>
            <p class="pbutton"><input type="button" class="button buttonRegister" value="goto login"
                      onclick='location.href="${pageContext.request.contextPath}/operations/login"'>
            </p>
        </form>
    </div>
</div>
</body>
</html>