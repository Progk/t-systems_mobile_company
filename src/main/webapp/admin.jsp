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
                        <li><a href="#addPlan" data-toggle="tab">Add plan</a></li>
                        <li><a href="#addOptions" data-toggle="tab">Add options</a></li>
                        <li><a href="#allUsers" data-toggle="tab">All Users</a></li>
                    </ul>
                    <div id="my-tab-content" class="tab-content">

                        <%--New Client--%>
                        <div class="tab-pane active" id="newClient">

                        </div>

                        <%--Edit User--%>
                        <div class="tab-pane" id="editUsers">

                        </div>

                        <%--Add plan--%>
                        <div class="tab-pane" id="addPlan">

                        </div>

                        <%--Add options--%>
                        <div class="tab-pane" id="addOptions">

                        </div>

                        <%--All Users--%>
                        <div class="tab-pane" id="allUsers">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>