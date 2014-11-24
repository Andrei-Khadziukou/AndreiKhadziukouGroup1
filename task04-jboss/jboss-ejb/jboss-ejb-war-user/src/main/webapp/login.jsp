<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="<c:url value='/css/sign.css' />"/>
</head>

<body>

    <div class="container">

        <form class="form-signin" method="post" action="j_security_check">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="text" name="j_username" class="form-control" placeholder="Username" required="required"
                   autofocus="autofocus"/>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="j_password" class="form-control" placeholder="Password" required="required"/>
            <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="Sign in"/>
        </form>

    </div>

</body>
</html>