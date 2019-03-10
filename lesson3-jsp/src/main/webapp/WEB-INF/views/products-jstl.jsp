<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- HTML комментарий. Будет виден в браузере -->
<%-- JSP комментарий. Присутствует только в коде. В браузере не отображается --%>

<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>

    <%-- При помощи requestScope мы получаем доступ к аттрибутам --%>
    <%-- которые были сохранены в контекст в коде сервлета --%>
    <%-- Данный коду в файле products.jsp, но использует JSTL теги --%>
    <c:forEach items="${requestScope.products}" var="product">

        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="products_jstl?id=${product.id}&command=EDIT">Edit</a>
                <a href="products_jstl?id=${product.id}&command=DELETE">Delete</a>
            </td>
        </tr>

    </c:forEach>
</table>

<a href="products_jstl?command=NEW">New product</a>

</body>
</html>
