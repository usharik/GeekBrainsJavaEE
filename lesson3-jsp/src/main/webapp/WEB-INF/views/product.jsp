<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Products</title>
</head>

<body>

<jsp:include page="header.jsp" />

<h2>Edit product with id ${product.id}</h2>
<form method="post" action="products_jstl">
    <%-- Скрытое поле ввода для передачи параметра, который нужен серверу, --%>
    <%-- но не должен отображаться на форме --%>
    <input type="hidden" id="id" name="id" value="${product.id}">

    <%-- Для вводимых параметров, передаваемых запросом, --%>
    <%-- обязательно должен быть задан атрибут name --%>
    <%-- Только в этом случае сервлет его сможет извлечь вызовом req.getParameter("attr_name_value")  --%>
    <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${product.name}" required>
    </p>
    <p>
        <label for="price">Price</label>
        <input type="number" id="price" name="price" value="${product.price}" required>
    </p>
    <input type="submit" value="Save">
    <input type="button" value="Cancel" onclick="window.location.href='products_jstl'">
</form>

</body>