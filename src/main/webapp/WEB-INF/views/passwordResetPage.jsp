<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ include file="/WEB-INF/fragments/meta.jsp" %>
    <title>Reset hasła</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/fragments/header.jsp" %>
</header>

<section class="login-page">
    <h2>Wpisz email:</h2>
    <form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email"/>
        </div>
        <div class="form-group">
            <c:if test="${param.error != null && param.error.equals('')}">
                <div class="form--steps-container">
                    <p>Nie ma takiego adresu email w bazie.</p>
                </div>
            </c:if>
            <c:if test="${param.good != null && param.good.equals('')}">
                <div class="form--steps-container">
                    <p>Email. wysłany. Sprawdź adres email.</p>
                </div>
            </c:if>
            <%--          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>--%>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Wyślij</button>
            <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>

<%@ include file="/WEB-INF/fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
