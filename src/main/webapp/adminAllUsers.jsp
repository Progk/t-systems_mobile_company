<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 12.07.15
  Time: 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Contract</th>
        <th>Blocked</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="entry" items="${allSimpleUsersMap}" varStatus="loop">
        <tr>
            <td>${entry.key.name}</td>
            <td>${entry.key.surname}</td>
            <td>${entry.key.email}</td>
            <td>
                <button type="button" class="btn btn-info" data-toggle="modal"
                        data-target="#modalShowContract" id="allUser_${entry.key.email}" onclick="allUserShowContract(this)">Show
                </button>
            </td>


            <td>
                <c:choose>
                    <c:when test="${entry.value eq false}">
                        <button type="button" class="btn btn-success" id="lockUser">
                            No
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-danger" id="lockUser">
                            Yes
                        </button>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Modal -->
<div class=modalShowContractWrapper">
<div id="modalShowContract" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">&times;</button>
                <h4 class="modal-title">${allUserShowContract.name}&nbsp;${allUserShowContract.surname}</h4>
            </div>
            <div class="modal-body">
                <h4>Contracts:</h4>
                <br>
                <ul> <c:forEach var="contract" items="${allUserShowContract.contracts}">
                    <li>${contract.number}</li>
                    </c:forEach>
                </ul>
                <%--<select class="form-control">
                    <c:forEach var="contract"
                               items="${allUserShowContract.contracts}">
                        <option value="${contract.number}">${contract.number}</option>
                    </c:forEach>
                </select>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">Close
                </button>
            </div>
        </div>

    </div>
</div>
</div>