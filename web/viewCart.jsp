<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 9:05:37 AM
    Author     : ADMIN
--%>

<%@page import="java.util.Map"%>
<%@page import="quannm.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1 class="title">Your Cart</h1>
        <c:set var="listItems" value="${sessionScope.CART.items}"/>
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
                <tbody>
                <form style="margin-bottom: 20px" action="DispatcherServlet" method="POST">
                    <c:forEach var="items" items="${listItems}" varStatus="theCount">   
                        <tr>
                            <td>
                                ${theCount.count}
                            </td>
                            <td>
                                ${items.value.name}
                            </td>
                            <td>
                                ${items.value.quantity}
                            </td>
                            <td>
                                ${items.value.description}
                            </td>
                            <td>
                                ${items.value.unitPrice}
                            </td>
                            <td>
                                ${items.value.unitPrice * items.value.quantity}
                                <c:set var="totalBill"      
                                       value="${totalBill + items.value.unitPrice * items.value.quantity}"/>
                            </td>
                            <td>
                                ${items.value.status}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" 
                                       value= "${items.value.sku}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7">
                            <a href="DispatcherServlet?btAction=Go to shopping">Add More Books to Your Cart</a> 
                        </td>
                        <td>
                            <input type="submit" 
                                   value="Remove Selected Items" 
                                   name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
        <c:set var="errors" value="${requestScope.ERROR_ORDER}"/>
        <form action="DispatcherServlet">
            Name <input style="margin: 5px 15px" type="text" name="txtName" value="${param.txtName}" /> </br>
            <c:if test="${not empty errors.nameCustomerLengthError}" >
                <font style="color: red">
                ${errors.nameCustomerLengthError} </br>
                </font>
            </c:if>
            Address <input style="margin: 5px 0px" type="text" name="txtAddress" value="${param.txtAddress}" /> </br>
            <c:if test="${not empty errors.addressLengthError}" >
                <font style="color: red">
                ${errors.addressLengthError} </br>
                </font>
            </c:if>
            <input type="hidden" name="txtTotal" value="${totalBill}" />
            <input style="margin-top: 10px" type="submit" value="CheckOut" name="btAction" />
        </form>
    </c:if>
    <c:set var="cart" value="${requestScope.CART_SHOW}"/>
    <c:set var="dto" value="${requestScope.ORDER}"/>
    <c:set var="message_checkOut" value="${requestScope.CHECKOUT_MESSAGE}"/>

    <c:if test="${empty listItems && empty dto.id}">
        <h2>
            <font color = "red"> No cart is existed!!! </font> </br>
        </h2>
        <c:url var="shoppingLink" value="DispatcherServlet">
            <c:param name="btAction" value="Go to shopping" />
        </c:url>                             
        <a href="${shoppingLink}">Go to Shopping</a>
    </c:if>

        
        
        
    <c:if test="${not empty dto.id}">

        <h2 style="color: red">Your Bill Order</h2>
        <table border="1">
            <thead>
                <tr>
                    <th colspan="10">Your Order</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="10">Order Id: ${dto.id}</td>
                </tr>

                <tr>
                    <td colspan="10">Address: ${dto.address}</td>
                </tr>

                <tr>
                    <td colspan="10">Date: ${dto.date}</td>
                </tr>

                <tr>
                    <td colspan="10" style="text-align: center; font-weight: bold">Your Products</td>
                </tr>

                <tr>
                    <td>Name: </td>
                    <td>Quantity: </td>
                    <td>Total: </td>
                </tr>
                <c:forEach var="listProducts" items="${cart.items}" >
                    <tr>
                        <td>
                            ${listProducts.value.name}
                        </td>
                        <td>
                            ${listProducts.value.quantity}
                        </td>
                        <td>
                            ${listProducts.value.unitPrice * listProducts.value.quantity}
                        </td>
                    </tr>
                </c:forEach>
                <tr> 
                    <td colspan="10">                        
                        <h2 style="color: red">Total Bill: ${dto.total}</h2>
                    </td>
                </tr>
            </tbody>
        </table> </br>
        <c:url var="shoppingLink" value="DispatcherServlet">
            <c:param name="btAction" value="Go to shopping" />
        </c:url>
        <a style="margin-top: 10px" href="${shoppingLink}">Go to Shopping</a>
    </c:if>
</body>
</html>
<%-- 
    <%
        //1. Cust goes to his/her cart place
        //có sẵn Session Scope vì nó là Oblitcit Object
        //check cart phải có tồn tại thì mới show 
--%>        <%--

            
            != null) {
            //2. Cust takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            //kiểm tra giỏ đồ có tồn tại
            if (cart != null) {
                //3. Cust gets items
                Map<String, Integer> items = cart.getItems();
                //kiểm tra ngắn chứa đồ tồn tại
                if (items != null) {
    %> 
    <form action="DispatcherServlet">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {
                %> 
            <form action="DispatcherServlet">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItem" 
                               value= "<%= key%>" />
                    </td>
                </tr>

                <%
                    }//traverse items
                %>
                <tr>
                    <td colspan="3">

                        <a href="shopping.jsp">Add More Books to Your Cart</a> 
                    </td>
                    <td>
                        <input type="submit" 
                               value="Remove Selected Items" 
                               name="btAction" />
                    </td>
                </tr>
            </form>

            </tbody>
        </table>
    </form>

--%>
<%-- 
    <%
                    return;
                }
            }
        }

        //4. Cust shows all items
%>
--%>
<!--
    <h2>
        <font color = "red">
        No cart is existed!!!
        <font/>

    </h2>-->