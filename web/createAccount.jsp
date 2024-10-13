<%-- 
    Document   : createAccount
    Created on : Mar 8, 2024, 8:08:59 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>
            Registration
        </h1>
        <form action="DispatcherServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 characters) </br>            
            <c:if test="${not empty errors.usernameLengthError}" >
                <font style="color: red">
                ${errors.usernameLengthError} </br>
                </font>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}" >
                <font style="color: red">
                ${errors.usernameIsExisted} </br>
                </font>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 characters)</br>
            <c:if test="${not empty errors.passwordLengthError}" >
                <font style="color: red">
                ${errors.passwordLengthError} </br>
                </font>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /></br>
            <c:if test="${not empty errors.confirmNotMatched}" >
                <font style="color: red">
                ${errors.confirmNotMatched} </br>
                </font>
            </c:if>
            Full name <input type="text" name="txtFullName" value="${param.txtFullName}" />(2 - 50 characters)</br>
            <c:if test="${not empty errors.fullNameLengthError}" >
                <font style="color: red">
                ${errors.fullNameLengthError} </br>
                </font>
            </c:if>

            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset"/>
        </form>    
    </body>
</html>
