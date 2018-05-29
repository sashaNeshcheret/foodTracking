<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html><head><title>Registration</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <input type="hidden" name="command" value="registrationParam"/>
    <input type="hidden" name="login" value="${login}" />
    <h1>Enter your personal information</h1>
    <br/>
    <c:if test="${not empty errorMessageParam}">
        <h5 style="color:#ff0000">${errorMessageParam}</h5>
    </c:if>

    <br/>Sex:<br/>
    <select name="sex">
        <option value="male">Male</option>
        <option selected value="female">Female</option>
    </select>
    <br/>Life style:<br/>
    <select name="lifeStyle">
        <option selected value="1">Sat lifestyle</option>
        <option selected value="2">Low activity (sport 1-2 a week)</option>
        <option selected value="3">Middle activity (sport 3-5 a week)</option>
        <option selected value="4">High activity (sport every day)</option>
        <option selected value="5">Very intensive activity (hard physical work, exersices several times a day)</option>
    </select>
    <br/>age:<br/>
    <input type="text" name="age" value="18" required=""/>
    <br/>height:<br/>
    <input type="text" name="height" value="170" required=""/>
    <br/>weight:<br/>
    <input type="text" name="weight" value="70" required=""/>
    <br/>Expected result:<br/>
    <select name="expectedResult">
        <option selected value="1">Put on weight</option>
        <option selected value="2">Save weight</option>
        <option selected value="3">put off weight</option>
    </select>
    <input type="submit" value="End registration"/>
</form>
<hr/>
<a href="/login.jsp">Log on if not registated</a><br/>
</body></html>