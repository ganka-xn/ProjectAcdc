<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>

<div class="container">
    <form class="form-horizontal" action="question" method="post">
        <fieldset>
            <legend>${requestScope.question.get().text}</legend>
            <input type="hidden" name="id" value="${requestScope.question.get().id}">
            <div class="form-group">
                <%--@declare id="answer"--%><label class="col-md-4 control-label" for="answer"></label>
                <div class="col-md-4">
                    <div class="radio">
                        <c:forEach var="answerId" items="${requestScope.question.get().getAnswers()}">
                            <c:set var="currentAnswer" value="${requestScope.answers.get(answerId)}" />
                            <label for="answer">
                                <input type="radio"
                                       name="answer"
                                       value="${answerId}">
                                    ${currentAnswer.get().text}
                            </label>
                            <br>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <button type="submit"
                                id="singlebutton"
                                name="singlebutton"
                                class="btn btn-success">Ok</button>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<c:import url="parts/footer.jsp"/>
