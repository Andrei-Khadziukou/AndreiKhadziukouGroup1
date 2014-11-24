<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Adding new account">
    <h2 class="form-signin-heading">Please fill this form</h2>
    <form action="<c:url value='/accounts' />" method="POST">
        <label>Serial number</label>
        <input type="text" name="serial"/>
        </br>
        <label>Full name</label>
        <input type="text" name="name"/>
        </br>
        <label>Age</label>
        <input type="text" pattern="\d{1,3}" name="age"/>

        <input type="submit" value="Add account"/>
    </form>
</s:html>