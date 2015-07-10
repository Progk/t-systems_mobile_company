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
                <h3><b>Client:</b>&nbsp;&nbsp;${user.name}&nbsp;${user.surname}</h3>
                <h3><b>Contract:</b>
                <select class="form-control" id="selectContract">
                    <c:forEach var="contract" items="${user.contracts}" >
                        <option value="${contract.number}">${contract.number}</option>
                    </c:forEach>
                </select>
                </h3>
            </div>

            <div class="panel-body" id="panelBody">
                <div id="content">
                    <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                        <li class="active"><a href="#currentContract" data-toggle="tab">Contract</a></li>
                        <li><a href="#changePlan" data-toggle="tab">Change Plan</a></li>
                        <li><a href="#options" data-toggle="tab">Options</a></li>
                    </ul>

                    <div id="my-tab-content" class="tab-content">
                        <div class="tab-pane active" id="currentContract">
                            <br>
                            <c:choose>
                                <c:when test="${empty contractPlan}">
                                    <h4><c:out value="${errorMessage}"/></h4>
                                </c:when>
                                <c:otherwise>
                                    <h4><b>Plan: </b>${contractPlan}</h4>
                                    <br>
                                    <h4><b>Phone number: </b>+${contractNumber}</h4>
                                    <br>
                                    <h4><b>Block Contract: </b>&nbsp;
                                        <div class="lockButtonWrapper"
                                        <c:choose>
                                            <c:when test="${contractLockTypeId==1}">
                                                <div class="lockButtonWrapper">
                                                <button type="button" class="btn btn-danger disabled" id="lockButton" onclick="clickLockButton()">
                                                    Lock by
                                                    Admin
                                                </button>
                                        </div>
                                            </c:when>
                                            <c:when test="${contractLockTypeId==2}">
                                                <div class="lockButtonWrapper">
                                                <button type="button" class="btn btn-warning active" id="lockButton" onclick="clickLockButton()">
                                                    Lock by User
                                                </button>
                                        </div>
                                            </c:when>
                                            <c:otherwise>
                                            <div class="lockButtonWrapper">
                                                <button type="button" class="btn btn-success" id="lockButton" onclick="clickLockButton()">Not locked
                                                </button>
                                        </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </h4>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="tab-pane" id="changePlan">
                            <br>
                            <c:choose>
                                <c:when test="${empty availablePlanList}">
                                    <h4>${errorMessage}</h4>
                                </c:when>
                                <c:otherwise>
                                        <select class="form-control" id="selectPlan">
                                            <option value="None">None</option>
                                            <c:forEach var="plan" items="${availablePlanList}">
                                                <option value="${plan.name}">${plan.name}</option>
                                            </c:forEach>
                                        </select>
                                        <br>
                                        <button type="button" class="btn btn-default" onclick="clickSelectPlanButton()">Select</button>
                                </c:otherwise>
                            </c:choose>
                        </div>


                        <div class="tab-pane" id="options">
                            <br>
                            <c:choose>
                                <c:when test="${empty availablePlanList}">
                                    <h4>${errorMessage}</h4>
                                </c:when>
                                <c:otherwise>
                            <div class="selectOptionWrapper">
                                <div class="col-sm-4" id="selectOption">
                                    <form class="form-horizontal" role="form" method="post">
                                        <c:forEach var="entry" items="${optionsForPlanMap}">
                                            <c:choose>
                                                <c:when test="${entry.value eq false}">
                                                    <div class="checkbox col-sm-offset-1">
                                                        <label><input type="checkbox" name="optionPlan" onclick="selectOptionForPlanByUser()" value="${entry.key.name}">${entry.key.name}
                                                        </label>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="checkbox disabled col-sm-offset-1">
                                                        <label><input type="checkbox" name="optionPlan" onclick="selectOptionForPlanByUser()" value="${entry.key.name}" disabled>${entry.key.name}
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <br>
                                        <button type="submit" class="btn btn-default" >Select</button>
                                    </form>
                                    </div>
                                </div>
                                </c:otherwise>
                            </c:choose>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>