<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h3>Client</h3>

    <div id="content">
        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#currentContract" data-toggle="tab">Contract</a></li>
            <li><a href="#changeTariff" data-toggle="tab">Change Tariff</a></li>
            <li><a href="#options" data-toggle="tab">Options</a></li>
        </ul>
        <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active" id="currentContract">
                <h1>Current Contract</h1>
            </div>
            <div class="tab-pane" id="changeTariff">
                <h1>Change Tariff</h1>
            </div>
            <div class="tab-pane" id="options">
                <h1>Options</h1>
            </div>
        </div>
    </div>
</div>
</body>

</html>