<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="center">
    <div id="main">
        <p>Ошибка! Что-то пошло не так!</p>
        <a href=${pageContext.request.contextPath}>Вернутся</a>
    </div>
</div>
</body>
</html>