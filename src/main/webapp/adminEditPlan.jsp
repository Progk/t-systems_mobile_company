<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 12.07.15
  Time: 4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover" id="editPlanTable">
    <thead>
    <tr>
        <th>Name</th>
        <th>Cost</th>
        <th>Options</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody >
    <c:forEach var="plan" items="${planList}">
        <td>${plan.name}</td>
        <td>${plan.price}</td>
        <td>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button"
                        data-toggle="dropdown">Show Plan
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <c:forEach var="options" items="${plan.options}">
                        <li><h4>${options.name}</h4></li>
                    </c:forEach>
                </ul>
            </div>
        </td>
        <%--dropdown--%>
        <td>
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                    data-target="#modal${plan.name}">Edit Plan
            </button>
        </td>
        <!-- Modal -->
        <div id="modal${plan.name}" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit</h4>
                    </div>

                    <div class="modal-body">
                        <form role="form" id="formEdit${plan.name}" onsubmit="updatePlan(this);return false;">
                            <div role="form" id="updatePlan">
                                <input type="hidden" name="plan" value="${plan.name}">
                                <input type="hidden" name="type" value="editAdminPlan">

                                <div class="form-group">
                                    <label for="planNewName">Name:</label>
                                    <input type="text" class="form-control" name="planNewName" value="${plan.name}"
                                           id="planNewName">
                                </div>

                                <div class="form-group">
                                    <label for="planNewPrice">Price:</label>
                                    <input type="number" class="form-control" name="planNewPrice" value="${plan.price}"
                                           id="planNewPrice">
                                </div>

                                <label>Options:</label>
                                <c:forEach var="option" items="${allOptions}">
                                    <div class="checkbox col-sm-offset-1">
                                        <label><input type="checkbox" name="editPlan${plan.name}"
                                                      value="${option.name} " class="btn btn-default">${option.name}

                                        </label>
                                    </div>
                                </c:forEach>
                                <input type="submit"/>

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-default" data-dismiss="modal">
                                        Save
                                    </button>
                                </div>

                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        </tr>
    </c:forEach>

    </tbody>
</table>
