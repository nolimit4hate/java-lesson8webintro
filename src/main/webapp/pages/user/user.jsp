<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
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
            <p>nickname <b>${requestScope.userToShow.name}</b></p>
            <p>role is  <b>${requestScope.userToShow.userRolePool}</b></p>
            <p>FIO      <b>${requestScope.userToShow.fio}</b></p>
            <p>country  <b>${requestScope.userToShow.country}</b></p>
            <p>crdate   <b>${requestScope.userToShow.crdate}</b></p>
        </div>
    </div>
</body>
</html>