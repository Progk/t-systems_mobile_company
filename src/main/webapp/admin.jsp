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

                            <form class="form-horizontal" role="form" id="newClientForm" action="/AdminUpdateServlet"
                                  method="post">
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="name">Name:</label>

                                    <div class="col-sm-4">

                                        <input type="text" class="form-control col-sm-offset-1" id="name" name="name"
                                               placeholder="Enter name" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-1" for="surname">Surname:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="surname"
                                               name="surname" placeholder="Enter surname" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-1" for="date">Date:</label>

                                    <div class="col-sm-4">
                                        <input type="date" class="form-control col-sm-offset-1" id="date" name="date"
                                               placeholder="Enter Date" required>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="passport">Passport:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="passport"
                                               name="passport" placeholder="Enter Passport" required>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="address">Address:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="address"
                                               name="address" placeholder="Enter address" required>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="email">Email:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="email" name="email"
                                               placeholder="Enter email" required>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="password">Password:</label>

                                    <div class="col-sm-4">
                                        <input type="password" class="form-control col-sm-offset-1" id="password"
                                               name="password" placeholder="Enter password" required>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="number">Number:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="number"
                                               name="number"
                                               placeholder="Enter phone number" required>
                                    </div>
                                </div>
                                <input type="hidden" name="type" value="addNewUser">
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="selectPlanNewUser">Plan:</label>

                                    <div class="col-sm-4">
                                        <select class="form-control col-sm-offset-1" id="selectPlanNewUser">
                                            <c:forEach var="plan" items="${allPlanList}">
                                                <option value="${plan.name}">${plan.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1">Options:</label>

                                    <div class="selectNewClientOptionWrapper">
                                        <div class="col-sm-4" id="selectNewClientOption">
                                            <c:forEach var="entry" items="${optionsForPlanMap}">
                                                <c:choose>
                                                    <c:when test="${entry.value eq false}">
                                                        <div class="checkbox col-sm-offset-1">
                                                            <label><input type="checkbox" name="optionNewPlan" onclick="selectOptionForPlanNewUser()" value="${entry.key.name}">${entry.key.name}
                                                            </label>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="checkbox disabled col-sm-offset-1">
                                                            <label><input type="checkbox" name="optionNewPlan" onclick="selectOptionForPlanNewUser()" value="${entry.key.name}" disabled>${entry.key.name}
                                                            </label>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-1 col-sm-4">
                                        <button type="submit" class="btn btn-default">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <%--Edit User--%>
                        <div class="tab-pane" id="editUsers">
                            <br>

                            <div class="editUserSearchWrapper">
                                <h4><label for="enterNumber">Enter Phone:</label></h4>
                                <input type="text" class="form-control" id="enterNumber">
                                <input type="submit" id="searchNumber" value="Search"/>
                            </div>

                            <hr>

                            <br>
                            <br>

                            <div class="editUserWrapper">
                                <h4><b>Current plan: </b>Plan</h4>
                                <h4><b>Number: </b>Number</h4>
                                <br>
                                <label class="control-label col-sm-1" for="selectPlan">Plan:</label>

                                <div class="col-sm-4">
                                    <select class="form-control col-sm-offset-1" id="selectPlan">
                                        <c:forEach var="contract" items="${user.contracts}">
                                            <option value="${contract.number}">${contract.number}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <%--Edit plan--%>
                        <div class="tab-pane" id="editPlans">
                            <h4><b>Select Plan:</b>
                                <select class="form-control" id="selectContract">
                                    <c:forEach var="contract" items="${user.contracts}">
                                        <option value="${contract.number}">${contract.number}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" id="deletePlan" value="Delete"/>
                            </h4>
                            <hr>
                            <h4>Add Plan</h4>

                            <form class="form-horizontal" role="form" action="/AdminUpdateServlet" method="post">
                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="namePlan">Name plan:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control col-sm-offset-1" id="namePlan"
                                               name="namePlan"
                                               placeholder="Enter plan name">
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-sm-1" for="planCost">Cost:</label>

                                    <div class="col-sm-4">
                                        <input type="number" class="form-control col-sm-offset-1" id="planCost"
                                               name="planCost" placeholder="Enter cost">
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-sm-1">Select options:</label>

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
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Surname</th>
                                    <th>Email</th>
                                    <th>Contract</th>
                                    <th>Block</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Petr</td>
                                    <td>Petrov</td>
                                    <td>petr@gmail.com</td>
                                    <td><input type="button" value="Show"></td>
                                    <td>
                                        <button type="button" class="btn btn-danger disabled" id="lockUser">
                                            Lock
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>