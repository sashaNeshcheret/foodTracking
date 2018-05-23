<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="servlet">
    <h1>Введіть логін і пароль</h1>
    <br/>
    <c:if test="${not empty errorMessageLogin}">
        <h5 style="color:#ff0000">${errorMessageLogin}</h5>
    </c:if>
    <input type="hidden" name="command" value="login" />
    Логін:<br/>
    <input type="text" name="login" value=""/>
    <br/>Пароль:<br/>
    <input type="password" placeholder="A-Z,a-z,1-9, all 4 char" name="password" value=""/>
    <br/>
    <input type="submit" value="Log in"/>
</form><hr/>
<form name="general" method="GET" action="servlet">
    <input type="hidden" name="command" value="goToRegistration" />
    <input type="submit" value="Перейти до реєстрації"/>
</form>
</body></html>