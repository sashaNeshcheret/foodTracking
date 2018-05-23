<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your day</title></head>
<body>
<h1 align="center">Enjoy your day</h1>

<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Вийти"/>
</form>

<h3 align="center">Your eaten products today and it's parameter</h3>

<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" align="center" valign="center">
        <td width="40%">Назва продукту</td>
        <td width="8%">Кількість</td>
        <td width="8%">Енергетична цінність</td>
        <td width="8%">білки</td>
        <td width="8%">вуглеводи</td>
        <td width="8%">жири</td>
        <td width="8%">час прийому</td>
        <td width="8%">дія</td>
    </tr>
</table>
<form align="center" name="addEatenProducts" method="GET" action="servlet">
    <c:forEach var = "products" items = "${eatenProductsList}">
        <table width="80%" border="1" cellpadding="7"  align="center">
            <tr colspan="2" align="center" valign="center">
                <td width="40%" height="3">${products.name}</td>
                <td width="8%" height="3">${products.weight}</td>
                <td width="8%" height="3">${products.energyValue}</td>
                <td width="8%" height="3">${products.proteins}</td>
                <td width="8%" height="3">${products.carbohydrates}</td>
                <td width="8%" height="3">${products.fats}</td>
                <td width="8%" height="3">${products.date}</td>
                <td width="8%" height="3">
                    <form name="chooseCategory" method="GET" action="servlet">
                        <input type="hidden" name="command" value="deleteEatenProduct" />
                        <input type="hidden" name="productId" value=${products.id} height="2" />
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>
</form>
<table width="80%" border="1" cellpadding="7"  align="center">
    <tr colspan="2" align="center" valign="center">
        <td width="48%" height="3">Загальна кількість</td>
        <td width="8%" height="3">${eatenProduct.energyValue}</td>
        <c:if test="${eatenProduct.energyValue == 0}">
            <td width="8%" height="3">${eatenProduct.proteins}</td>
            <td width="8%" height="3">${eatenProduct.carbohydrates}</td>
            <td width="8%" height="3">${eatenProduct.fats}</td>
        </c:if>
        <c:if test="${eatenProduct.energyValue != 0}">
            <td width="8%" height="3">${eatenProduct.proteins} or ${eatenProduct.proteins*100/eatenProduct.energyValue}%</td>
            <td width="8%" height="3">${eatenProduct.carbohydrates} or ${eatenProduct.carbohydrates*100/eatenProduct.energyValue}%</td>
            <td width="8%" height="3">${eatenProduct.fats} or ${eatenProduct.fats*100/eatenProduct.energyValue}%</td>
        </c:if>
        <td width="8%" height="3"></td>
        <td width="8%">
        </td>
    </tr>
</table>
<c:if test="${(not empty message)}">
    <h5 align="center" >${message}</h5>
</c:if>
<c:if test="${(not empty messageWarning)}">
    <h5 align="center" style="color:#ffff00">${messageWarning}</h5>
</c:if>
<c:if test="${(not empty messageError)}">
    <h5 align="center" style="color:#ff0000">${messageError}</h5>
</c:if>

<c:if test="${not empty errorMessage}">
    <h5 align="center" style="color:#ff0000">${errorMessage}</h5>
</c:if>
<br/>
<table width="40%" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr colspan="2"  valign="top" align="center">
        <td width="20%">
            <form align="right" name="goToGeneral" method="GET" action="servlet">
                <input type="hidden" name="command" value="goToGeneral" />
                <input type="submit" value="go on main"/>
            </form>
        </td>
        <td width="20%">
            <form align="left" name="goToChooseCategory" method="GET" action="servlet">
                <input type="hidden" name="command" value="goToChooseCategory" />
                <input type="submit" value="or go to choose category"/>
            </form>
        </td>
    </tr>
</table>




</body></html>