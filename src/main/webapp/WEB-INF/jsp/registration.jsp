<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html><head><title>Registration</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <h1>Заповніть відповідні поля</h1>
    <br/>
    <c:if test="${not empty errorMessageLogin}">
        <h5 style="color:#ff0000">${errorMessageLogin}</h5>
    </c:if>
    <input type="hidden" name="command" value="registration" />
    <br/>Login:<br/>
    <input type="text" name="login" value="" required=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value="" required=""/>
    <br/>Name:<br/>
    <input type="text" name="name" value="" required=""/>
    <br/>Surname:<br/>
    <input type="text" name="surname" value="" required=""/>
    <br/>
    <br/>Mail address:<br/>
    <input type="text" name="mailAddress" value="" required=""/>
    <br/>
    <input type="submit" value="Ввести особисті параметри"/>

</form><hr/>
<!--<input type="submit" value="або пропусти і перейти на головну"/>
-->

<a href="/login.jsp">Увійти, якшо ви зареєстровані</a><br/>
</body></html>