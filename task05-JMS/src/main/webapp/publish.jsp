<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Publisher</title>

    <link rel="stylesheet" href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css">
    <link rel="stylesheet" href="http://getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css">
    <link rel="stylesheet" href="<c:url value='/css/main.css' />"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

</head>

<body>
<div class="container">
    <c:if test="${not empty errors}">
        <div class="alert alert-danger" role="alert">
            <c:forEach var="error" items="${errors}">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <c:out value="${error}" /> </br>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert" role="alert">
            <c:out value="${success}"/>
        </div>
    </c:if>
    <div class="hero-unit">
        <form class="form-horizontal" method="GET" action="<c:url value='/publish' />">
            <fieldset>

                <div class="control-group">
                    <label>Topic</label>
                    <input type="text" name="topic"/>
                </div>

                <div class="control-group">
                    <label>Message</label>
                    <input type="text" name="message"/>
                </div>
                <input class="btn btn-primary" type="submit" value="publish"/>
            </fieldset>
        </form>
    </div>
</div>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>
