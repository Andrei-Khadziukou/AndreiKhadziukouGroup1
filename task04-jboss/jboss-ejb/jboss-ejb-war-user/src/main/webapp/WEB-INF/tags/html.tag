<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="titleKey" required="false" rtexprvalue="true" type="java.lang.String" %>

<c:if test="${not empty titleKey}">
    <fmt:message key="${titleKey}" var="title" />
</c:if>

<!DOCTYPE html>

<html dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:if test="${not empty title}">
        <title><c:out value="${title}" /></title>
    </c:if>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <link rel="stylesheet" href="<c:url value='/css/main.css' />"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

</head>

<body>
  <s:nav />
  <div class="container">
      <c:if test="${not empty errors}">
            <div class="alert alert-danger" role="alert">
              <c:forEach var="error" items="${errors}">
              <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
              <c:out value="${error}" /> </br>
              </c:forEach>
            </div>
      </c:if>
      <jsp:doBody />
  </div>
</body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>
