<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:html title="Main" isNav="false">
<div class="jumbotron hero-unit">
    <form class="form-horizontal" action="/cinema/home" method="POST">
      <div class="form-group">
        <label for="userName" class="col-sm-2 control-label">User</label>
        <div class="col-sm-10">
          <select name="userName" class="form-control">
            <c:forEach items="${users}" var="user">
                   <option><c:out value="${user.name}"/></option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default">Sign in</button>
        </div>
      </div>
    </form>
</div>
</s:html>