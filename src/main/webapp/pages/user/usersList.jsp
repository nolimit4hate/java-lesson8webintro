<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controllers.ConstAttributeNames" %>

<html>
<head>
    <c:set scope="page" var="usersList" value="${ConstAttributeNames.USERS_LIST}"></c:set>
    <title>All users</title>
    <style>
        .sub{
            color: black
        }
    </style>
</head>
<body>
    <ul>
        <c:forEach items="${requestScope.get(pageScope.usersList)}"  var="users">
            <li style="color: firebrick">name: <b>${users.name}</b>
            <ul>
                <li class="sub">role: ${users.userRolePool}</li>
                <li class="sub">fio: ${users.fio}</li>
                <li class="sub">country: ${users.country}</li>
                <li class="sub">creation date: ${users.crDate}</li>
                <li class="sub">password: ${users.password}</li>
            </ul>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
