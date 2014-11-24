<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Exchange rates digest">
    <div class="panel panel-default">
      <div class="panel-heading">Exchange rates</div>
        <table class="table">
            <tr>
                <th>Relate</th>
                <th>Rate</th>
            </tr>
            <c:forEach var="ex" items="${digest.exchangeRates}">
                <tr>
                  <td><c:out value="${ex.id}"/>
                  <td><c:out value="${ex.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</s:html>