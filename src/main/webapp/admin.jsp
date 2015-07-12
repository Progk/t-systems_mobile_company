<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>

<body>
<div class="containerWrapper" id="containerWrapper">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3><b>Admin:</b>&nbsp;&nbsp;${user.name}&nbsp;${user.surname}</h3>
            </div>

            <div class="panel-body" id="panelBody">
                <div id="content">
                    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                        <li class="active"><a href="#newClient" data-toggle="tab">New Client</a></li>
                        <li><a href="#editUsers" data-toggle="tab">Edit User</a></li>
                        <li><a href="#editPlans" data-toggle="tab">Edit plans</a></li>
                        <li><a href="#editOptions" data-toggle="tab">Edit options</a></li>
                        <li><a href="#allUsers" data-toggle="tab">All Users</a></li>
                    </ul>
                    <div id="my-tab-content" class="tab-content">

                        <%--New Client--%>
                        <div class="tab-pane active" id="newClient">
                            <br>
                            <%@ include file="adminNewUserForm.jsp" %>
                        </div>

                        <%--Edit User--%>
                        <div class="tab-pane" id="editUsers">
                            <br>
                            <%@ include file="adminEditUser.jsp" %>
                        </div>

                        <%--Edit plan--%>
                        <div class="tab-pane" id="editPlans">
                            <%@ include file="adminEditPlan.jsp" %>
                        </div>

                        <%--Add options--%>
                        <div class="tab-pane" id="editOptions">
                            <h4><b>Select Option:</b>
                                <select class="form-control" id="selectOption">
                                    <c:forEach var="contract" items="${user.contracts}">
                                        <option value="${contract.number}">${contract.number}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" id="deleteOption" value="Delete"/>
                            </h4>
                            <hr>
                            <h4>Add Option</h4>

                            <form class="form-horizontal" role="form" action="/AdminUpdateServlet" method="post">
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="nameOption">Name Option:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="nameOption"
                                               name="nameOption"
                                               placeholder="Enter plan name">
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="optionCost">Cost:</label>

                                    <div class="col-sm-4">
                                        <input type="number" class="form-control col-sm-offset-1" id="optionCost"
                                               name="planCost" placeholder="Enter cost">
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="optionCostConnection">Cost
                                        Connect:</label>

                                    <div class="col-sm-4">
                                        <input type="number" class="form-control col-sm-offset-1"
                                               id="optionCostConnection"
                                               name="optionCostConnection" placeholder="Enter cost connection">
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-sm-1">Blocked options:</label>

                                    <div class="col-sm-4">
                                        <c:forEach var="contract" items="${user.contracts}">
                                            <div class="checkbox">
                                                <label><input type="checkbox" value="${option.name}">${option.name}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <%--All Users--%>
                        <div class="tab-pane" id="allUsers">
                            <%@ include file="adminAllUsers.jsp" %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>