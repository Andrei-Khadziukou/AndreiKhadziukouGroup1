<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:html title="Main">
<div class="jumbotron">
    <form action="/cinema/order" method="POST">
      <div class="form-group">
        <label>Session name: </label>
        <label class="value"><c:out value="${ticket.name}"/></label>
      </div>
      <div class="form-group">
        <label>Cost: </label>
        <label class="value"><c:out value="${ticket.cost}"/></label>
      </div>
      <div class="form-group">
          <label for="places">Places</label>
          <input type="text" class="form-control" name="places" id="places" placeholder=" e.g: 1,3,5..."/>
          <input type="hidden" name="id" value="<c:out value='${ticket.id}'/>" />
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</s:html>