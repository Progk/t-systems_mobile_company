<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 12.07.15
  Time: 4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Name</th>
        <th>Cost</th>
        <th>Options</th>
        <th>Edit</th>
    </tr>
    </thead>

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
        <td>
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
                    data-target="#modal${plan.name}">Open Modal
            </button>
        </td>
        <!-- Modal -->
        <div id="modal${plan.name}" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form" id="updatePlan" action=""
                              method="post">
                            <div class="form-group ">
                                <label class="control-label col-sm-1" for="planNewName">Name:</label>

                                <div class="col-sm-4">
                                    <input type="text" class="form-control col-sm-offset-1"
                                           id="planNewName"
                                           name="planNewName" value="${plan.name}"
                                           placeholder="Enter name of plan" required>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="control-label col-sm-1" for="planNewPrice">Price:</label>

                                <div class="col-sm-4">
                                    <input type="text" class="form-control col-sm-offset-1"
                                           id="planNewPrice"
                                           name="planNewName" value="${plan.price}"
                                           placeholder="Enter price of plan" required>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>

            </div>
        </div>


        </tr>
    </c:forEach>

    </tbody>
</table>
