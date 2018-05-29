<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Registration</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <input type="hidden" name="command" value="registrationParam" />
    <input type="hidden" name="login" value="${login}"/>
    <h2>Enter parameters</h2>
    <c:if test="${not empty errorLoginPassMessage}">
        <h5 style="color:#ff0000">${errorLoginPassMessage}</h5>
    </c:if>
    <br/>Sex:<br/>

    <select name="sex">
        <option <c:if test="${userParam.sex eq 'female'}">selected</c:if> value="female">Female</option>
        <option <c:if test="${userParam.sex eq 'male'}">selected</c:if> value="male">Male</option>
    </select>

    <br/>Life style:<br/>
    <select name="lifeStyle">
        <option <c:if test="${userParam.lifeStyleId == 1}">selected</c:if> value="1">Sat lifestyle</option>
        <option <c:if test="${userParam.lifeStyleId == 2}">selected</c:if> value="2">Low activity (sport 1-2 a week)</option>
        <option <c:if test="${userParam.lifeStyleId == 3}">selected</c:if> value="3">Middle activity (sport 3-5 a week)</option>
        <option <c:if test="${userParam.lifeStyleId == 4}">selected</c:if> value="4">High activity (sport every day)</option>
        <option <c:if test="${userParam.lifeStyleId == 5}">selected</c:if> value="5">Very intensive activity (hard physical work, exersices several times a day)</option>
    </select>
    <br/>age:<br/>
    <input type="text" name="age" value="${userParam.age}" required=""/>
    <br/>height:<br/>
    <input type="text" name="height" value="${userParam.height}" required=""/>
    <br/>weight:<br/>
    <input type="text" name="weight" value="${userParam.weight}" required=""/>
    <br/>Expected result:<br/>
    <select name="expectedResult">
        <option selected value="1">Put on weight</option>
        <option selected value="2">Save weight</option>
        <option selected value="3">Put off weight</option>
    </select>
    <input type="submit" value="Enter"/>
</form>
<hr/>
<a href="/login.jsp">Log on if you registrated</a><br/>
</body></html>