<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rating Page</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="center">
    <div id="main">
        <h2>Вы вошли в систему под именем : ${name}!</h2>
        <a href="game.jsp">Играть</a>
        <c:if test="${users.size()>0}">
            <h3>Рейтинг:</h3>
            <table border="1">
                <tr>
                    <th>Имя</th><th>Рейтинг</th><th>Количество игр</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.getName()}</td>
                        <td>${user.getRating()}</td>
                        <td>${user.getNumberOfGames()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${users.size()==0}">
            <p>Рейтинг пользователей пуст!</p>
        </c:if>

        <br/><br/>
        <a href="logout">Выйти</a>
    </div>
</div>
</body>
</html>
