<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="container container--70">
    <c:if test="${!isLogin}">
        <ul class="nav--actions">
            <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>
    </c:if>
    <c:if test="${isLogin}">
        <ul class="nav--actions">
            <li><a href="<c:url value="/logout"/>" class="btn btn--small btn--without-border" onclick="return confirm('Czy na pewno chcesz się wylogować?')">Wyloguj</a></li>

        </ul>
    </c:if>

    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
        <li><a href="<c:url value="/#steps"/>" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="<c:url value="/#about-us"/>" class="btn btn--without-border">O nas</a></li>
        <li><a href="<c:url value="/#fund"/>" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="<c:url value="/user/form"/>" class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="<c:url value="/#contact"/>" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>

