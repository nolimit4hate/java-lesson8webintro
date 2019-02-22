<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
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
            <c:if test="${requestScope.nouser=='userexists'}">
                <p class="pfield" align="center" style="color:black;">User or email already exists</p>
            </c:if>
            <p class="pfield">
                <input type="text" class="fields" name="username" required="required" placeholder="Nickname">
            </p>
            <p class="pfield">
                <input type="email" class="fields" name="email" required="required" placeholder="email">
            </p>
            </p class="pfield">
            <input type="password" class="fields" name="password" required="required" placeholder="password">
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