<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="navbar navbar-default" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
      </button>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value='/' />">Tickets</a></li>
        <li><a href="<c:url value='/order' />">Make order</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value='/logout' />">Logout</a></li>
      </ul>
    </div>
  </div>
</div>
