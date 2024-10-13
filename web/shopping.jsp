 
    Document   : shopping
    Created on : Mar 9, 2024, 12:01:40 PM
    Author     : ADMIN


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <form action="DispatcherServlet">
            Products: <select style="height: 30px; margin-bottom: 10px" name="cboProduct">
                <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}"></c:set>
                <c:forEach var="dto" items="${listProduct}">
                    <option value="${dto.sku}"> ${dto.name} </option>
                </c:forEach>
            </select>
            <input type="submit" value="Add Product To Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>

    </body>
</html>

