<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <c:if test="${requestScope.restart}">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/question" method="get">
            <fieldset>

                <!-- Form Name -->
                <legend>${requestScope.message}</legend>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <button type="submit" id="singlebutton" name="singlebutton"class="btn btn-primary">Сыграть снова</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </c:if>
</div>

<c:import url="parts/footer.jsp"/>