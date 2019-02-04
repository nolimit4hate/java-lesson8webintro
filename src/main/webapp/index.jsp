<%--
  Created by IntelliJ IDEA.
  User: nodota
  Date: 01.02.19
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>WLCM2H</title>
  <style>
    body{
      background: #000;
      text: "white";
    }
    .block{
      padding: 10px;
      border-radius: 10px;
      background: #60a839;
    }
    .parent{
      width: 100%;
      height: 100%;
      position: fixed;
      top: 0;
      left: 0;
      display: flex;
      align-items: center;
      align-content: center;
      justify-content: center;
      overflow: auto;
    }
    .mainForm{

    }
  </style>
</head>
<body>
<div class="parent">
  <div class="block">
    <form class = "mainForm" name="authForm" method="post">
      <p><b>Nickname</b></br>
        <input type="text" size="25">
      </p>
      <p><b>Password</b></br>
        <input type="password" size="25">
      </p>
      <p>
        <input type="submit" value="submit">
      </p>
    </form>
  </div>
</div>
</body>
</html>