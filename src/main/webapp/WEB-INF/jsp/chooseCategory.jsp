<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your day</title></head>
<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Вийти"/>
</form>
<!--<h2 align="center">Enjoy your day tracking food</h2>-->

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
            <td width="55%" height="2">${category.name}</td>
            <td width="20%" height="2">
                <form align="center" valign="center" height="2%" name="chooseCategory" method="GET" action="servlet">
                    <input type="hidden" name="command" value="chooseCategory" height="2" />
                    <input type="hidden" name="id" value=${category.id} height="2" />
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