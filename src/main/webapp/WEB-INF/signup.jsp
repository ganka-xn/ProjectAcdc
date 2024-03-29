<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<h1>Регистрация</h1>
<form action="signup" method="post">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Зарегистрироваться">
</form>

<c:import url="parts/footer.jsp"/>