<%-- 
    Document   : search
    Created on : Jan 30, 2024, 8:58:45 AM
    Author     : ADMIN
--%>

<%@page import="quannm.loginn.loginnDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red" style="margin-bottom: 100px"> 
        Welcome, ${sessionScope.USERINFO.fullName}
        </font>
        <form action="DispatcherServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value = "${param.txtSearchValue}" /><br>
            <input style="margin-bottom: 50px" type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHR_RESULT}"/>
            <c:if test="${empty result}">
                <div style="color: red;">No record is matched!!!</div>
            </c:if>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatcherServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername"
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="password" name="txtPassword"
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin"
                                           value="ON"
                                           <c:if test="${dto.role}">
                                               checked = checked
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatcherServlet">
                                        <c:param name="btAction" value="delete" />
                                        <!--tuyet doi khong co khoang trang trong nhay doi-->
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
    <form action="DispatcherServlet">
        <input style="margin-top: 50px" type="submit" value="Logout" name="btAction" />
    </form>
    <c:url var="testShoppingLink" value="DispatcherServlet">
        <c:param value="Go to test shopping" name="btAction"></c:param>
    </c:url>
    <a href="${testShoppingLink}">Go to test shopping</a>
    <c:url var="shoppingLink" value="DispatcherServlet">
        <c:param name="btAction" value="Go to shopping" />
    </c:url>
    <a href="${shoppingLink}">Go to shopping</a>
</body>
</html>
<%-- Làm welcome và log out --%>
<%--
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        Cookie newestCookie = cookies[cookies.length - 1];
        String username = newestCookie.getName();
        //String password = newestCookie.getValue();
%>  
<font color="red"> 
    Welcome, <%= username %>
<!--        Welcome, <%= request.getAttribute("jsession")%> -->
</font>
<%
    }
%>
<h1>Search PAGE</h1>
<!--        <form action="SearchServlet" method="POST">
           Name: <input type="text" name="Name" value="" />
           <input style="border: 1px solid blue " type="submit" value="Search" />
        </form>-->
<form action="DispatcherServlet">
    Search Value <input type="text" name="txtSearchValue" 
                        value="<%= request.getParameter("txtSearchValue")%>" /><br>
    <input type="submit" value="Search" name="btAction" />
</form>
<%
    String searchValue = request.getParameter("txtSearchValue");
    //khi nạp lần đầu tiên có khả năng = null
    //Servlet không có khái niệm = null vì đã nạp rồi 
    if (searchValue != null) {
        //giá trị search nằm ở request scope vì request.setAttribute() 
        //kiểu dữ liệu List DTO
        List<loginnDTO> result
                = (List<loginnDTO>) request.getAttribute("SEARCHR_RESULT");

                if (result != null) { //information is found
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (loginnDTO dto : result) {
                        String urlRewriting = "DispatcherServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                //goi lai chuc nang search 1 lan nua
                                + "&lastSearchValue=" + searchValue;
                        //xoa xong roi thi refresh 
%>
            <form action="DispatcherServlet" method="GET">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%> "    />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//role is admin
%>
                               />

                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>

            <%
                }//end get each dto in result
            %>
        </tbody>
    </table>

    <%
    } else { //no record is matched
        //frangment code/ vung code viet html
    %>
    <h2>
        <font color="red">
        No record is matched
        </font>
    </h2>
    <%
            } //end no record is matched
        }//end search value is called second times or request from user
    %>
--%>

