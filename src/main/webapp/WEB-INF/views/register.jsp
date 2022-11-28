<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ include file="/WEB-INF/fragments/meta.jsp" %>
    <title>Rejestracja</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/fragments/header.jsp" %>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="name" type="text" name="name" placeholder="Imię"/>
            <form:errors path="name"></form:errors>
        </div>
        <div class="form-group">
            <form:input path="surname" type="text" name="surname" placeholder="Nazwisko"/>
            <form:errors path="surname"></form:errors>
        </div>
        <div class="form-group">
            <form:input path="email" type="email" name="email" placeholder="Email"/>
            <form:errors path="email"></form:errors>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło"/>
            <form:errors path="password"></form:errors>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Resetuj hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Załóż konto</button>
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</section>

<%--footer--%>
<%@ include file="/WEB-INF/fragments/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
