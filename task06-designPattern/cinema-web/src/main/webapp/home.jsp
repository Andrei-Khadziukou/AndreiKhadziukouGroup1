<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:html title="Main">
        <table class="table table-striped">
        <thead>
            <tr>
                <td><b>Session name</b></td>
                <td><b>Cost</b></td>
                <td><b>Amount</b></td>
                <td><b>Session time start</b></td>
                <td><b>Session time end</b></td>
                <td></td>
            </tr>
        </thead>
            <c:if test="${not empty tickets}">
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                            <td><c:out value="${ticket.name}"/></td>
                            <td><c:out value="${ticket.cost}"/></td>
                            <td><c:out value="${ticket.count}"/></td>
                            <td><fmt:formatDate pattern="HH:mm" value="${ticket.sessionTimeStart}" /></td>
                            <td><fmt:formatDate pattern="HH:mm" value="${ticket.sessionTimeEnd}" /></td>
                            <td><a class="btn btn-default btn-xs" href="/cinema/order?id=<c:out value='${ticket.id}'/>">Buy</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
</s:html>