<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<table width="80%" border="0" cellpadding="0" cellspacing="0" align="right">
    <tr colspan="2"  valign="top">
        <td align="left" width="70%" height="2%" >
            <c:if test="${not empty userContact.name}">
                <c:out value="Hello, ${userContact.name} in tracking food programm! Good luck!">
                </c:out>
            </c:if>
        </td>
        <td width="30%" height="2%">
            <form align="right" name="LogOut" method="GET" action="servlet">
                <input type="hidden" name="command" value="logOut" />
                <input type="submit" value="Log out"/>
            </form>
        </td>
    </tr>
</table>
</br>
<c:if test="${userResult.id>0}">
    <h3 align="center">your current parameters</h3>
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2" bgcolor="#D3EDF6" valign="top" align="center">
            <td width="15%">Sex</td>
        <!--    <td width="20%">Life's style</td> -->
            <td width="15%">Age</td>
            <td width="15%">Height</td>
            <td width="15%">Weight</td>
            <td width="20%">Level metabolism</td>
            <td width="20%">Norma calories</td>

        </tr>
        <tr colspan="2"  valign="top" align="center">
            <td width="15%">${userParam.sex}</td>
        <!--    <td width="20%">${userParam.lifeStyleId}</td> -->
            <td width="15%">${userParam.age}</td>
            <td width="15%">${userParam.height}</td>
            <td width="15%">${userParam.weight}</td>
            <td width="20%">${userResult.levelMetabolism}</td>
            <td width="20%">${userResult.normaCalories}</td>
        </tr>
    </table>
    <form align="center" name="goToChangeParam" method="GET" action="servlet">
        <input type="hidden" name="command" value="goToChangeParam" />
        <input type="submit" value="Change data"/>
    </form>
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
    </table>
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
        <tr colspan="2"  valign="top" align="center">
            <td width="40%" height="2%" bgcolor="#D3EDA6">your consumed calories
                <c:if test="${eatenProduct.energyValue > 0}">
                    <form align="center" name="lookProducts" method="GET" action="servlet">
                        <input type="hidden" name="command" value="addAndShowEatenProducts" />
                        <input type="hidden" name="pageNumber" value="0" />
                        <input type="hidden" name="categoryId" value="1" />
                        <input type="submit" name ="Add All Products" value="look eaten products"/>
                    </form>
                </c:if>
            </td>
            <td width="20%" height="2%">${eatenProduct.energyValue}</td>
            <td width="15%" height="2%">${eatenProduct.proteins}</td>
            <td width="15%" height="2%">${eatenProduct.carbohydrates}</td>
            <td width="15%" height="2%">${eatenProduct.fats}</td>
        </tr>
    </table>
    <table width="80%" border="2" cellpadding="7" cellspacing="0" align="center">
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
<c:if test="${empty userResult}">
    <form align="center" name="registrationParam" method="GET" action="servlet">
        <input type="hidden" name="command" value="goToRegistrationParam" />
        <input align="center" style="color:#ff0000" type="submit" value="You must have enter your parameters"/>
    </form>
</c:if>
<h4 align="center">To add eaten products choose category</h4>
<form align="center" name="choseCategory" method="GET" action="servlet">
    <input type="hidden" name="command" value="goToChooseCategory" />
    <input type="submit" value="Choose"/>
</form>
</body></html>