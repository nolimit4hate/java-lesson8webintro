<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="controllers.AttributeName" %>

<html>
<head>
    <c:set scope="page" var="showUser" value="${AttributeName.SHOW_USER}"></c:set>
    <title>Title</title>
    <style>
        b{color: navy;}
        <jsp:include page="/pages/css/base.css"></jsp:include>
        <jsp:include page="/pages/css/signinForm.css"></jsp:include>
    </style>
</head>
<body>
    <div class="parent">
        <div class="block blockLogin">
            <p>nickname <b>${requestScope.get(pageScope.showUser).name}</b></p>
            <p>role is  <b>${requestScope.get(pageScope.showUser).userRolePool}</b></p>
            <p>FIO      <b>${requestScope.get(pageScope.showUser).fio}</b></p>
            <p>country  <b>${requestScope.get(pageScope.showUser).country}</b></p>
            <p>crdate   <b>${requestScope.get(pageScope.showUser).crdate}</b></p>
        </div>
    </div>
</body>
</html>