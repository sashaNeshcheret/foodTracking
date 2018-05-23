<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html><head><title>Registration</title></head>
<body>
<form name="registrationForm" method="POST" action="servlet">
    <input type="hidden" name="command" value="registrationParam"/>
    <input type="hidden" name="login" value="${login}" />
    <h1>Заповніть відповідні поля</h1>
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
        <option selected value="1">Сидячий спосіб життя</option>
        <option selected value="2">Низька активність(спорт 1-2 рази на тиждень)</option>
        <option selected value="3">Середня активність(спорт 3-5 разів на тиждень)</option>
        <option selected value="4">Висока активність(спорт кожного дня)</option>
        <option selected value="5">Дуже висока активність(важка фізична праця,
            тренування кілька разів на день)</option>
    </select>
    <br/>age:<br/>
    <input type="number" name="age" value="18" required=""/>
    <br/>height:<br/>
    <input type="number" name="height" value="170" required=""/>
    <br/>weight:<br/>
    <input type="number" name="weight" value="70" required=""/>
    <br/>Expected result:<br/>
    <select name="expectedResult">
        <option selected value="1">Набрати вагу</option>
        <option selected value="2">Зберегти вагу</option>
        <option selected value="3">Зхуднути</option>
    </select>
    <input type="submit" value="End registration"/>
</form>
<hr/>
<a href="/login.jsp">Увійти, якшо ви зареєстровані</a><br/>
</body></html>