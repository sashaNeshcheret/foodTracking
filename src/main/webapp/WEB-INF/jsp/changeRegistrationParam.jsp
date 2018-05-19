<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Registration</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <input type="hidden" name="login" value="${login}"/>
    <h1>Заповніть відповідні поля</h1>
    <br/>
    <c:if test="${not empty errorLoginPassMessage}">
        <h5 style="color:#ff0000">${errorLoginPassMessage}</h5>
    </c:if>
    <input type="hidden" name="command" value="registrationParam" />
    <br/>Sex:<br/>

    <select name="sex">
        <option <c:if test="${userParam.sex eq 'female'}">selected</c:if> value="female">Female</option>
        <option <c:if test="${userParam.sex eq 'male'}">selected</c:if> value="male">Male</option>
    </select>

    <br/>Life style:<br/>
    <select name="lifeStyle">
        <option <c:if test="${userParam.lifeStyleId == 1}">selected</c:if> value="1">Сидячий спосіб життя</option>
        <option <c:if test="${userParam.lifeStyleId == 2}">selected</c:if> value="2">Низька активність(спорт 1-2 рази на тиждень)</option>
        <option <c:if test="${userParam.lifeStyleId == 3}">selected</c:if> value="3">Середня активність(спорт 3-5 разів на тиждень)</option>
        <option <c:if test="${userParam.lifeStyleId == 4}">selected</c:if> value="4">Висока активність(спорт кожного дня)</option>
        <option <c:if test="${userParam.lifeStyleId == 5}">selected</c:if> value="5">Дуже висока активність(важка фізична праця,
            тренування кілька разів на день)</option>
    </select>
    <br/>age:<br/>
    <input type="number" name="age" value="${userParam.age}" required=""/>
    <br/>height:<br/>
    <input type="number" name="height" value="${userParam.height}" required=""/>
    <br/>weight:<br/>
    <input type="number" name="weight" value="${userParam.weight}" required=""/>
    <br/>Expected result:<br/>
    <select name="expectedResult">
        <option selected value="1">Набрати вагу</option>
        <option selected value="2">Зберегти вагу</option>
        <option selected value="3">Зхуднути</option>
    </select>
    <input type="submit" value="Завершити реєстрацію"/>
</form>
<hr/>
<a href="/login.jsp">Увійти, якшо ви зареєстровані</a><br/>
</body></html>