<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your day</title></head>
<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Вийти"/>
</form>
<!--<h2 align="center">Enjoy your day tracking food</h2>-->
<h3 align="center">Your eaten products today and it's parameter</h3>

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
<!--    <input type="hidden" name="command" value="addAndShowEatenProducts" />
    <input type="hidden" name="categoryId" value=${categoryId} />-->
    <c:forEach var = "products" items = "${eatenProductsList}">
        <table width="80%" border="1" cellpadding="7"  align="center">
            <tr colspan="2"  valign="top" align="center">
                <td width="25%" height="3">${products.productId}</td>
                <td width="20%" height="3">${products.weight}</td>
                <td width="20%" height="3">${products.energyValue}</td>
                <td width="10%" height="3">${products.proteins}</td>
                <td width="10%" height="3">${products.carbohydrates}</td>
                <td width="10%" height="3">${products.fats}</td>
            </tr>
        </table>
    </c:forEach>
</form>

<h5 align="center">Add eaten products, but first choose category</h5>

<table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
    <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
        <td width="55%">Назва категоії</td>
        <td width="20%">Обрати</td>
    </tr>
</table>

<c:forEach var = "category" items = "${list}">
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2"  valign="center" align="center">
            <td width="55%" height="2%">${category.name}</td>
            <td width="20%" height="2%">
                <form align="center" valign="center" height="2%" name="chooseCategory" method="GET" action="servlet">
                    <input type="hidden" name="command" value="chooseCategory" height="2%" />
                    <input type="hidden" name="id" value=${category.id} height="2%" />
                    <input type="submit" value="Choose"/>
                </form>
            </td>
        </tr>
    </table>
</c:forEach>
</br>
<form align="center" name="AddCategory" method="GET" action="servlet">
    <input type="hidden" name="command" value="AddCategory" />
    <input type="submit" value="or add new category"/>
</form>

</body></html>