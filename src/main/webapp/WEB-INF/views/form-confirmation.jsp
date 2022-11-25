<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <%@ include file="/WEB-INF/fragments/meta.jsp" %>
    <title>Podsumowanie</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
  </head>
  <body>
  <%--header--%>
  <%@ include file="/WEB-INF/fragments/header.jsp" %>

  <%--footer--%>
  <%@ include file="/WEB-INF/fragments/footer.jsp" %>

  <script src="<c:url value="resources/js/app.js"/>"></script>
  </body>
</html>
