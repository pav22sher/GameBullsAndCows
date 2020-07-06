<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="center">
    <div id="main">
        Добро пожаловать!
        <p>${loginStatus}</p>
        <form method="post" action="login">
            <table>
                <tr>
                    <td><input type="text" name="name" placeholder="имя" required /></td>
                </tr>
                <tr>
                    <td><input type="password" name="password" placeholder="пароль" required /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Войти" /></td>
                </tr>
                <tr>
                    <td><a href="registration.jsp">Регистрация</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
