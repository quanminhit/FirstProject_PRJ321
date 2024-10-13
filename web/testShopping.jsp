<%-- 
    Document   : testShopping
    Created on : Mar 18, 2024, 10:54:38 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Shopping Page</title>
    </head>
    <body>
        <c:set var="listDTO" value="${requestScope.TEST_LIST_PRODUCT}"/>
        <c:if test="${not empty listDTO}">
            <form action="DispatcherServlet">
                Product <select style="margin-bottom: 10px" name="chkProduct">
                    <c:forEach var="dto" items="${listDTO}">
                        <option value="${dto.sku}">${dto.name}</option>
                    </c:forEach>
                </select> </br>
                <input type="submit" value="TestAddToCart" name="btAction" />
                <input type="submit" value="TestViewCart" name="btAction" /> </br>
            </form>
        </c:if>
        <c:set var="errors" value="${requestScope.ERROR_CART}"/>
        <c:if test="${not empty errors.quantityError}">
            <font style="color: red">
            ${errors.quantityError}
            </font>
        </c:if>
        <c:if test="${not empty errors.statusError}">
            <font style="color: red">
            ${errors.statusError}
            </font>
        </c:if>
    </body>
</html>
