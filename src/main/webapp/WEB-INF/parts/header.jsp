<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<header class="bg-light d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/"
       class="d-flex align-items-left col-md-1 mb-2 mb-md-0 text-dark text-decoration-none">
        <img src="${pageContext.request.contextPath}/img/home.png" width="48" alt="Home page">
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <%-- guest --%>
        <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">Home</a></li>
        <%-- user --%>
        <li><a href="#" class="nav-link px-2 link-dark disabled">Статистика</a></li>
        <%-- admin--%>
        <li><a href="list-user" class="nav-link px-2">Пользователи</a></li>
        <li><a href="create-quest" class="nav-link px-2">Создать квест</a></li>
    </ul>

    <ul class="nav col-md-3 text-end">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><a href="profile" class="nav-link px-2 link-dark">Profile</a></li>
                <li><a href="logout" class="nav-link px-2 link-dark">Logout</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="login" class="nav-link px-2 link-dark">Login</a></li>
                <li><a href="signup" class="nav-link px-2 link-dark">Sign-up</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</header>




