<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"></c:set>
</head>
<body>
  <div style="position:absolute; width:100%; top:35%;">
    <h1 align="center">
      Hello ${sessionScope.user}
    </h1>
    <c:if test="${empty sessionScope.user}">
      <p align="center"><a href="${contextPath}/operations/login">
        goto <b style="color: #60a839">login</b> form</a></p>
      <p align="center"><a href="${contextPath}/operations/registration">
        goto <b style="color: darkred">registration</b> form</a></p>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
      <p align="center"><a href="${contextPath}/userslist">goto usersList</a></p>
      <p align="center"><a href="${contextPath}/user/${sessionScope.user}">
        goto your profile</a></p>
      <p align="center"><a href="${contextPath}/operations/logout" style="color: #BF3030">
        toLOGOUT</a></p>
    </c:if>
  </div>
</body>
</html>