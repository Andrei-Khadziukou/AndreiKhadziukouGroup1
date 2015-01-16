<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Users manage digest">
<div class="panel panel-default">
      <div class="panel-heading">User accounts manager</div>
        <table class="table">
            <tr>
                <th>Username</th>
                <th>Account name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="entry" items="${digestAdmin.userAccounts}">
            <tr>
              <td><c:out value="${entry.key}"/></td>

              <c:choose>
                  <c:when test="${entry.value != null}">
                     <td><c:out value="${entry.value.name}"/></td>
                     <td><input type="button" value="unassign"/></td>
                  </c:when>
                  <c:otherwise>
                     <td></td>
                     <td><input type="button" value="assign"/></td>
                  </c:otherwise>
              </c:choose>
            </tr>
            </c:forEach>
        </table>
    </div>
</s:html>