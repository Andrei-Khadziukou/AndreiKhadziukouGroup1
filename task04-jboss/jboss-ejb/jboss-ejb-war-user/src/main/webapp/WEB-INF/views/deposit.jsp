<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Depository digest">
    <label>New currency deposit</label>
            <select id="deposit" name="deposit">
             <c:forEach var="cur" items="${digest.currencies}">
              <option><c:out value="${cur}"/></option>
             </c:forEach>
            </select>
    <input type="button" class="btn btn-default btn-xs" id="depbutton" value="Add new deposit" />
    <button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#myModal">
                    Make transfer
    </button>
    <div class="panel panel-default">
        <div class="panel-heading"><c:out value="${account.name}"/>'s deposits</div>
        <table class="table">
        <tr>
            <th>Currency</th>
            <th>Value</th>
        </tr>
        <c:forEach var="entry" items="${deposits}">
        <tr>
              <td><c:out value="${entry.key.currency}"/></td>
              <td><c:out value="${entry.value.value}"/></td>
        </tr>
        </c:forEach>
        </table>
    </div>

    <script>
                $(document).ready(function(){
                     $("#depbutton").click(function () {
                          var cur = $("#deposit").val();
                          var path = "<c:url value='/deposits' />"
                          $.ajax({
                            url: path,
                            type: 'POST',
                            contentType: "application/x-www-form-urlencoded",
                            dataType: "text",
                            data: "deposit=" + cur,
                            success: function (msg) {
                                    location.reload();
                            }
                          });

                     });
                });
    </script>

    <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Transfer money</h4>
              </div>
              <div class="modal-body">
                    <h2 class="form-signin-heading">Please fill this form</h2>
                    <form role="form" id="depTrans" action="<c:url value='/deposits/transfer' />" method="POST" >
                     <div class="form-group">
                        <label>What currency</label>
                        <select name="srcCur" class="form-control">
                         <c:forEach var="entry" items="${deposits}">
                          <option><c:out value="${entry.key.currency}"/></option>
                         </c:forEach>
                        </select>
                     </div>
                     <div class="form-group">
                        <label for="fullname">To currency</label>
                        <select name="destCur" class="form-control">
                                  <c:forEach var="entry" items="${deposits}">
                                         <option><c:out value="${entry.key.currency}"/></option>
                                  </c:forEach>
                        </select>
                     </div>
                     <div class="form-group">
                        <label for="serialNUmber">Age</label>
                        <input type="text" class="form-control" id="serialNUmber" pattern="^\d*\.?\d*$" name="value" placeholder="Enter your age">
                     </div>
                    </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <input type="submit" class="btn btn-primary" value="Transfer" form="depTrans"/>
              </div>
            </div>
          </div>
        </div>


</s:html>