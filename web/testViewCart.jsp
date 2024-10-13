<%-- 
    Document   : testViewCart
    Created on : Mar 18, 2024, 4:28:31 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test View Cart Page</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART_TEST}"/>
        <c:set var="listItems" value="${cart.items}"/>
        <c:if test="${not empty listItems}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <form action="DispatcherServlet" method="POST"> 
                    <tbody>
                        <c:forEach var="dto" items="${listItems}" varStatus="theCount">
                            <tr>
                                <td>${theCount.count}</td>
                                <td>${dto.value.name}</td>
                                <td>${dto.value.quantity}</td>
                                <td>${dto.value.description}</td>
                                <td>${dto.value.unitPrice}</td>
                                <td>${dto.value.quantity * dto.value.unitPrice}</td>
                                <td>${dto.value.status}</td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${dto.value.sku}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="7">
                                <a href="DispatcherServlet?btAction=Go to test shopping">Test Add more product</a>
                            </td>
                            <td>
                                <input type="submit" value="Test Remove Selected Items" name="btAction" />
                            </td>
                        </tr>
                    </tbody>
                </form>
            </table>
            
        </c:if>
        
        <c:if test="${empty listItems}">
            <h2>
                <font color = "red"> No cart is existed!!! </font> </br>
            </h2>
            <a href="DispatcherServlet?btAction=Go to test shopping"h>Test go to shopping</a>
        </c:if>
            
            
    </body>
</html>
