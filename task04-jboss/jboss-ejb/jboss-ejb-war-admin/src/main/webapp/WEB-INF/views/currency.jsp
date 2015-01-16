<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Currency digest">
    <h1>Currency</h1>
    <form action="<c:url value='/currency' />" method="POST">
            <input type="text" name="currency" pattern="[A-Z]{3}"/>
            <input type="submit" value="Add"/>
    </form>
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
              <div class="panel-heading">
               Currency
              </div>
                <table class="table">
                    <tr>
                        <th>Currency name</th>
                        <th></th>
                    </tr>
                     <c:forEach var="cur" items="${digestAdmin.currencies}">
                    <tr>
                         <td><c:out value="${cur}"/></td>
                         <td>
                            <span id="remove" onClick="removeCur(this);" aria-hidden="true" class="glyphicon glyphicon-remove" cur="${cur}"></span>
                         </td>
                    </tr>
                     </c:forEach>
                </table>
            </div>

         </ul>
        </div>
    </div>

    <script>
        function removeCur(obj) {
                            var currency = $(obj).attr("cur");
                            var path = "<c:url value='/currency' />?currency=" + currency;
                            $.ajax({
                                    url: path,
                                    type: 'DELETE',
                                    success: function (msg) {
                                        location.reload();
                                    }
                             });
        }
    </script>


</s:html>