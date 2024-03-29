<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <h1 class="text-center">Players Statistics</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Games played</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="playerStatsEntry" items="${playerStatsMap}">
            <tr>
                <c:forEach var="user" items="${users}">
                    <c:if test="${user.id eq playerStatsEntry.value.playerId}">
                        <td>${user.login}</td>
                    </c:if>
                </c:forEach>
                <td>${playerStatsEntry.value.gamesPlayed}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<c:import url="parts/footer.jsp"/>
