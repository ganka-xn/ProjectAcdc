<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <h1 class="text-center">Login</h1>
    <form class="form-horizontal" action="login" method="post">
        <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label" for="selectUser">Select User</label>
                <div class="col-md-4">
                    <select id="selectUser" name="selectUser" class="form-control">
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}"
                                    <c:if test="${user.id eq param.selectUser}">selected</c:if>>
                                    ${user.login}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="passwordInput">Password Input</label>
                <div class="col-md-4">
                    <input id="passwordInput" name="passwordInput" type="password" placeholder="placeholder" class="form-control input-md">
                    <c:if test="${not empty errorMessage}">
                        <p style="color: red">${errorMessage}</p>
                    </c:if>
                    <span class="help-block">Enter your password</span>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="loginBtn"></label>
                <div class="col-md-8">
                    <button id="loginBtn" name="btnClicked" value="loginBtn" class="btn btn-success">Ok</button>
<%--                    <button id="reqBtn" name="btnClicked" value="regBtn" class="btn btn-primary">Registration</button>--%>
                </div>
            </div>

        </fieldset>
    </form>
</div>

<c:import url="parts/footer.jsp"/>