<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Enjoy your day</title></head>
<body>
<h1 align="center">Enjoy your day</h1>

<form align="right" name="LogOut" method="GET" action="servlet">
    <input type="hidden" name="command" value="logOut" />
    <input type="submit" value="Вийти"/>
</form>

<form align="center" name="addEatenProducts" method="GET" action="servlet">
    <table width="60%" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr colspan="2"  valign="center" align="center">
            <td width="25%">
                <c:if test = "${categoryId != 1}">
                    <input type="submit" name ="previous category" value="previous category"/>
                </c:if>
            </td>
            <td width="40%"><h4 align="center">Products category - ${categoryId}</h4></td>
            <td width="25%">
                <input type="submit" name ="next category" value="next category"/>
            </td>
        </tr>
        <tr colspan="0"  valign="center" align="center">
            <td width="25%"></td>
            <td width="40%"><h4 align="center">Choose product and it's number </h4></td>
            <td width="25%"></td>
        </tr>
    </table>
    <c:if test="${not empty errorMessage}">
        <h5 align="center" style="color:#ff0000">${errorMessage}</h5>
    </c:if>
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2" bgcolor="#D3EDF6" valign="center" align="center">
            <td width="45%">Назва продукту</td>
            <td width="50%">Кількість</td>
        </tr>
    </table>
    <input type="hidden" name="command" value="addAndShowEatenProducts" />
    <input type="hidden" name="categoryId" value=${categoryId} />
        <c:forEach var = "products" items = "${productsList}">
            <table width="80%" border="1" cellpadding="7"  align="center">
                <tr colspan="2"  valign="center" align="center">
                    <td width="45%" height="7%">${products.name}</td>
                    <td width="50%" height="7%"> Number :
                        <input type="hidden" name="id" value=${products.id} />
                        <input type="text" value="0" name="number"  required="" />
                    </td>
                </tr>
            </table>
        </c:forEach>
    <input align="center" type="submit" name ="Add All Products" value="Add All Products"/>
</form>


<form align="center" name="AddProduct" method="GET" action="servlet">
    <input type="hidden" name="command" value="AddProduct" />
        <h3 align="center">Add new own product</h3>
        <br/>
        <c:if test="${not empty errorLoginPassMessage}">
            <h5 style="color:#ff0000">${errorLoginPassMessage}</h5>
        </c:if>
        <input type="hidden" name="command" value="addNewProduct" />
        <input type="hidden" name="CategoryId" value="${categoryId}" />
        Name: <input type="text" name="name" value="" required=""/>

        Energy value <input type="number" name="energyValue" value="" required=""/>
        Proteins <input type="number" name="proteins" value="" required=""/>
        Carbohydrates <input type="number" name="carbohydrates" value="" required=""/>
        Fats <input type="number" name="fats" value="" required=""/>

        <input type="submit" value="Create product"/>
    </form>
</form>

</body></html>