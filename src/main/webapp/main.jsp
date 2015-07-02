<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 30.06.15
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="./js/main.js"></script>
</head>
<body>

<div class="login_class">
<h1>Login form</h1>
<form name="login_form" id="login_form" action="/LoginServlet"  method="post">
    <div class="form-element">
        <label for="user_mail">Email</label>
        <input type="email" id="user_mail" name="user_mail" placeholder="name@example.com" required>
    </div>
    <div class="form-element">
        <label for="user_pass">Password</label>
        <input type="password" id="user_pass" name="user_pass" placeholder="password" required>
    </div>
    <div class="form-element">
        <input type="submit" value="Login">
    </div>
</form>
</div>
</body>
</html>
