<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <%@ include file="/WEB-INF/fragments/meta.jsp" %>
    <title>Logowanie</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
  </head>
  <body>
    <header>
        <%@ include file="/WEB-INF/fragments/header.jsp" %>
    </header>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form method="post" >
        <div class="form-group">
          <input type="email" name="username" placeholder="Email" />
        </div>
        <div class="form-group">
          <input type="password" name="password" placeholder="Hasło" />
          <c:if test="${param.error != null && param.error.equals('')}">
          <div class="form--steps-container">
            <p>Nieprawidłowe dane logowania</p>
          </div>
          </c:if>
          <c:if test="${param.message != null && param.message.equals('invalidToken')}">
            <div class="form--steps-container">
              <p>Token nieważny.</p>
            </div>
          </c:if>
          <c:if test="${param.message != null && param.message.equals('expired')}">
            <div class="form--steps-container">
              <p>Link wygasł.</p>
            </div>
          </c:if>
          <c:if test="${param.change != null && param.message.equals('good')}">
            <div class="form--steps-container">
              <p>Hasło zmienione. Zaloguj się do systemu.</p>
            </div>
          </c:if>
          <a href="<c:url value="/reset"/>" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Zaloguj się</button>
          <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </section>

    <%@ include file="/WEB-INF/fragments/footer.jsp" %>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
  </body>
</html>
