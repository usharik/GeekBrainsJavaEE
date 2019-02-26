<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- HTML комментарий. Будет виден в браузере -->
<%-- JSP комментарий. Присутствует только в коде. В браузере не отображается --%>

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

    <%-- При помощи requestScope мы получаем доступ к аттрибутам --%>
    <%-- которые были сохранены в контекст в коде сервлета --%>
    <%-- Данный коду в файле products.jsp, но использует JSTL теги --%>
    <c:forEach items="${requestScope.products}" var="product">

    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
    </tr>

    </c:forEach>
</table>

<p>JSP with JSTL example</p>

</body>
</html>
