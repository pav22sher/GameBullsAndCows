<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Page</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="center">
    <div id="main">
        <h2>Вы вошли в систему под именем : ${name}!</h2>
        <p>
            ${gameStatus}
        </p>

        <c:if test="${!isGame}">
            <form method="post" action="start">
                <table>
                    <tr><td><input type="button" onclick="showGameRules()" value="Правила игры" /></td></tr>
                    <tr><td><input type="submit" value="Старт/Рестарт" /></td></tr>
                </table>
            </form>
        </c:if>
        <c:if test="${isGame}">
            <form method="post" action="try">
                <table>
                    <tr><td><input type="text" name="inputNumber" placeholder="число" required/></td></tr>
                    <tr><td><input type="submit" value="Угадать" /></td></tr>
                </table>
            </form>
        </c:if>

        <a href="rating">Рейтинг</a>

        <c:if test="${isGame!=null && attempts.size()>0}">
            <h3>История:</h3>
            <table>
                <c:forEach var="attemp" items="${attempts}">
                    <tr><td>${attemp}</td></tr>
                </c:forEach>
            </table>
        </c:if>

        <br/><br/>
        <a href="logout">Выйти из системы</a>
    </div>
</div>
<script>
    function showGameRules(){
        alert('Компьютер загадывает 4-х значное число. ' +
            'Цифры загаданного числа не повторяются. ' +
            'Задача пользователя угадать загаданное число. ' +
            'У пользователя неограниченное число попыток. ' +
            'В каждую попытку пользователь дает компьютеру ' +
            'свой вариант числа. Компьютер сообщает сколько ' +
            'цифр точно угадано (бык) и сколько цифр угадано ' +
            'без учета позиции (корова). По ответу компьютера ' +
            'пользователь должен за несколько ходов угадать число.');
    }
</script>
</body>
</html>
