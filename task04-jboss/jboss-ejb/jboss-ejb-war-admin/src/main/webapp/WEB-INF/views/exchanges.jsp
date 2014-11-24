<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Exchange rates digest">
    <div class="panel panel-default">
      <div class="panel-heading">
        <button type="button" class="btn btn-default " data-toggle="modal" data-target="#myModal">
              Add exchange rate
        </button>
      </div>
        <table class="table">
            <tr>
                <th>Relate</th>
                <th>Rate</th>
            </tr>
            <c:forEach var="ex" items="${digestAdmin.exchangeRates}">
                <tr>
                  <td><c:out value="${ex.id}"/>
                  <td><c:out value="${ex.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>


        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">New account form</h4>
              </div>
              <div class="modal-body">
                    <h2 class="form-signin-heading">Please fill this form</h2>
                    <form role="form" id="exchform" action="<c:url value='/exchanges' />" method="POST" >
                     <div class="form-group">
                        <label for="mainCurrency">Primary currency</label>
                        <input type="text" class="form-control" id="mainCurrency" pattern="[A-Z]{3}" name="srcCurrency" placeholder="Primary currency">
                     </div>
                     <div class="form-group">
                        <label for="scndCur">Seconary currency</label>
                        <input type="text" class="form-control" id="scndCur" pattern="[A-Z]{3}" name="destCurrency" placeholder="Secondary currency">
                     </div>
                     <div class="form-group">
                        <label for="value">Value</label>
                        <input type="text" class="form-control" id="serialNUmber" pattern="^\d*\.?\d*$" name="curValue" placeholder="Value">
                     </div>
                    </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <input type="submit" class="btn btn-primary" value="Add exchange" form="exchform"/>
              </div>
            </div>
          </div>
        </div>

</s:html>