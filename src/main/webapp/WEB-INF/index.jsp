<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <h1 class="text-center">Добро пожаловать в математический квест!</h1>
    <form class="form-horizontal" action="index" method="post">
        <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label" for="loginBtn"></label>
                <div class="col-md-8">
                    <button id="loginBtn"
                            name="btnClicked"
                            value="loginBtn"
                            class="btn btn-success">
                        Login</button>
<%--                    <button id="regBtn"
                            name="btnClicked"
                            value="regBtn"
                            class="btn btn-primary">
                        Registration</button>--%>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<c:import url="parts/footer.jsp"/>