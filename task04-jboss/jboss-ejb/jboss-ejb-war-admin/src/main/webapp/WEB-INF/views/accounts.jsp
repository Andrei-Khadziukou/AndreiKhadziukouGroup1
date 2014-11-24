<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>

<s:html title="Accounts' digest">
    <div class="panel panel-default">
      <div class="panel-heading">
          <button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#myModal">
                Add account
          </button>
      </div>
      <table class="table">
            <tr>
                <th>Name</th>
                <th>Serial number</th>
                <th>Age</th>
            </tr>
            <c:forEach var="acc" items="${digestAdmin.accounts}">
                 <tr>
                   <td><c:out value="${acc.name}"/></td>
                   <td><c:out value="${acc.serialNumber}"/></td>
                   <td><c:out value="${acc.age}"/></td>
                 </tr>
            </c:forEach>
      </table>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">New account form</h4>
          </div>
          <div class="modal-body">
                <h2 class="form-signin-heading">Please fill this form</h2>
                <form role="form" id="accdata" action="<c:url value='/accounts' />" method="POST" >
                 <div class="form-group">
                    <label for="serialNUmber">Serial number</label>
                    <input type="text" class="form-control" id="serialNUmber" name="serial" placeholder="Enter your serial number">
                 </div>
                 <div class="form-group">
                    <label for="fullname">Full name</label>
                    <input type="text" class="form-control" id="fullname" name="name" placeholder="Enter your full name">
                 </div>
                 <div class="form-group">
                    <label for="serialNUmber">Age</label>
                    <input type="text" class="form-control" id="serialNUmber" pattern="\d{1,3}" name="age" placeholder="Enter your age">
                 </div>
                </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <input type="submit" class="btn btn-primary" value="Add account" form="accdata"/>
          </div>
        </div>
      </div>
    </div>



</s:html>