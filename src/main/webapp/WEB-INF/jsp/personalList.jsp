<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your day</title></head>
<body>
<h1 align="center">Enjoy your day</h1>

<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Вийти"/>
</form>

<c:if test="${userResult.id>0}">
    <h3 align="center">your current parameters</h3>
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
            <td width="40%" align="center"></td>
            <td width="20%">Expected norma calories</td>
            <td width="15%">Proteins</td>
            <td width="15%">Carbohydrates</td>
            <td width="15%">Fats</td>
        </tr>
        <tr colspan="2"  valign="top" align="center">
            <td width="40%" bgcolor="#D3EDA6">your recommended result</td>
            <td width="20%">${userResult.expectedNormCalories}</td>
            <td width="15%">${userResult.proteins}</td>
            <td width="15%">${userResult.carbohydrates}</td>
            <td width="15%">${userResult.fats}</td>
        </tr>
        <tr colspan="2"  valign="top" align="center">
            <td width="40%" height="2%" bgcolor="#D3EDA6">your consumed calories</td>
            <td width="20%" height="2%">${eatenProduct.energyValue}</td>
            <td width="15%" height="2%">${eatenProduct.proteins}</td>
            <td width="15%" height="2%">${eatenProduct.carbohydrates}</td>
            <td width="15%" height="2%">${eatenProduct.fats}</td>
        </tr>
        <tr colspan="2"  valign="top" align="center">
            <td width="40%" height="2%" bgcolor="#D3EDA6">Available balance calories</td>
            <td width="20%" height="2%">${availableBalance.energyValue}</td>
            <td width="15%" height="2%">${availableBalance.proteins}</td>
            <td width="15%" height="2%">${availableBalance.carbohydrates}</td>
            <td width="15%" height="2%">${availableBalance.fats}</td>
        </tr>
    </table>
</c:if>
<c:if test="${(not empty message)}">
    <h5 align="center" >${message}</h5>
</c:if>
<c:if test="${(not empty messageWarning)}">
    <h5 align="center" style="color:#ffff00">${messageWarning}</h5>
</c:if>
<c:if test="${(not empty messageError)}">
    <h5 align="center" style="color:#ff0000">${messageError}</h5>
</c:if>

<h3 align="center">Your eaten products and it's parameter</h3>
<c:if test="${not empty errorMessage}">
    <h5 align="center" style="color:#ff0000">${errorMessage}</h5>
</c:if>
<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="25%">Назва продукту</td>
        <td width="20%">Кількість</td>
        <td width="20%">Енергетична цінність</td>
        <td width="10%">білки</td>
        <td width="10%">вуглеводи</td>
        <td width="10%">жири</td>
    </tr>
</table>
<form align="center" name="addEatenProducts" method="GET" action="servlet">
    <c:forEach var = "products" items = "${eatenProductsList}">
        <table width="80%" border="1" cellpadding="7"  align="center">
            <tr colspan="2"  valign="top" align="center">
                <td width="25%" height="7%">${products.productId}</td>
                <td width="20%" height="7%">${products.weight}</td>
                <td width="20%" height="7%">${products.energyValue}</td>
                <td width="10%" height="7%">${products.proteins}</td>
                <td width="10%" height="7%">${products.carbohydrates}</td>
                <td width="10%" height="7%">${products.fats}</td>
            </tr>
        </table>
    </c:forEach>
</form>
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