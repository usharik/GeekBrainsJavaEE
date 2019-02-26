<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.geekbrains.servlet.entity.Product" %>
<%@ page import="java.util.Collection" %>

<html>
<head>
    <title>Products</title>
</head>
<body>

<jsp:include page="header.jsp" />

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
    </tr>

    <% for (Product pr : (Collection<Product>) request.getAttribute("products")) { %>

    <tr>
        <td><%= pr.getId() %></td>
        <td><%= pr.getName() %></td>
        <td><%= pr.getPrice() %></td>
    </tr>

    <% } %>
</table>

<p>Pure JSP example</p>

</body>
</html>
