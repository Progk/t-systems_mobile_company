jQuery(document).ready(function ($) {
    //$('#selectPlan').tab();
    console.log("ready");

    $("#selectContract").on('change', function () {
        console.log("selected " + $(this).val());
        var sendData = {type: "selectContract", number: $(this).val()}
        $.ajax({
            url: "/UserUpdateServlet",
            method: "POST",
            data: sendData,
            async: false,
            success: function (result) {
                $('#content').load('user.jsp #content');
            }
        });
    });

    /*$("#selectPlan").on('change', function () {
        console.log("selected " + $(this).val());
        selectedPlan = $(this).val();
    });*/

    /*$("#selectPlan").on('change', function() {
     console.log("selected " + $(this).val());
     if ( $(this).val() != "") {
     var sendData = {type: "selectPlan", plan: $(this).val()}
     $.ajax({
     url: "/UserUpdateServlet",
     method: "POST",
     data: sendData,
     async: false,
     success: function (result) {
     $('#content').load('user.jsp #content');
     }
     });
     } else {
     alert("Choose plan!")
     }
     });*/


    /* $("#selectPlanForm").submit(function() {
     alert("aaa");
     return false; // avoid to execute the actual submit of the form.
     /!* console.log("selected " + $(this).val());
     if ( $(this).val() != "None") {
     $.ajax({
     url: "/UserUpdateServlet",
     type: "POST",
     async: false,
     data: $("#selectPlanForm").serialize(),
     success: function (data) {
     $('#content').load('user.jsp #content');
     }
     });
     } else {
     alert("Select Plan!")
     }*!/
     });*/


});

function clickLockButton() {
    console.log("lockButton click");
    var sendData = {type: "clickLockButton"}
    $.ajax({
        url: "/UserUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('.lockButtonWrapper').load('user.jsp #lockButton');
        }
    });
}

function clickSelectPlanButton() {
    var plan = $('#selectPlan').val();
    console.log(plan);
    if (plan == "None") {
        alert("Select Plan")
        return false;
    }
    var sendData = {type: "selectNewPlan", plan: plan}
    $.ajax({
        url: "/UserUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('#content').load('user.jsp #content');
        }
    });
    //selectNewPlan
    //newPlan
    /*console.log(document.getElementById("selectPlan").value)*/
}
