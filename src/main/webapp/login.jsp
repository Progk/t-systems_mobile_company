<!DOCTYPE html>
<html lang="en">

<head>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>

<body>
<div class="formWrapper">
<form class="form-horizontal" role="form" action="/LoginServlet" method="post">

    <div class="form-group ">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="email" name="email" placeholder="Enter email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="password">Password:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="password" name="password" placeholder="Enter password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
            <button type="submit" class="btn btn-default">Submit</button>
        </div>
    </div>
</form>
</div>
</body>
</html>