<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="center">
    <div id="main">
        Добро пожаловать!
        <p>${registrationStatus}</p>
        <form method="post" action="register">
            <table>
                <tr>
                    <td><input type="text" name="newname" placeholder="имя" required /></td>
                </tr>
                <tr>
                    <td><input type="password" name="newpassword" placeholder="пароль" required /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="login" value="Зарегистрироваться" /></td>
                </tr>
                <tr>
                    <td><a href="login.jsp">Вход в систему</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
