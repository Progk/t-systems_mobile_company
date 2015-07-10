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
                $('#content').load('user.jsp #content', function() {
                    //alert( "Load was performed." );
                    $.each(result, function(key, value) {
                        console.log(value);
                        $(":checkbox[value=" + value + "]").prop('checked', true);
                    });
                });
            }
        });
    });

    $("#newClientForm").submit(function() {
        console.log("newClientFormSubmit");
        var sendData = $("#newClientForm").serialize();
        $.ajax({
            type: "POST",
            url: "/AdminUpdateServlet",
            data: sendData,
            success: function(data)
            {
                console.log("success");
            }
        });
        return false; // avoid to execute the actual submit of the form.
    });

    $("#selectPlanNewUser").on('change', function () {
        console.log("selected plan " + $(this).val());
        var sendData = {type: "selectPlanNewClient", plan: $(this).val()}
        $.ajax({
            url: "/AdminUpdateServlet",
            method: "POST",
            data: sendData,
            async: false,
            success: function (result) {
                $('.selectNewClientOptionWrapper').load('admin.jsp #selectNewClientOption');
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

function selectOptionForPlanNewUser() {
    console.log("selected");
    var selectedArr = [];
    $("input:checkbox[name=optionNewPlan]:checked").each(function(){
        selectedArr.push($(this).val());
    });
    var sendData = {type: "selectedOptionArrNewClient", selectedOptionArr: selectedArr}
    $.ajax({
        url: "/AdminUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('.selectNewClientOptionWrapper').load('admin.jsp #selectNewClientOption', function() {
                //alert( "Load was performed." );
                selectedArr.forEach(function(entry) {
                    console.log(entry);
                    $(":checkbox[value=" + entry + "]").prop('checked', true);
                });
            });
        }

    });
}

function selectOptionForPlanByUser() {
    console.log("selected");
    var selectedArr = [];
    $("input:checkbox[name=optionPlan]:checked").each(function(){
        selectedArr.push($(this).val());
    });
    var sendData = {type: "selectOptionClient", selectedOptionArr: selectedArr}
    $.ajax({
        url: "/UserUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('.selectOptionWrapper').load('user.jsp #selectOption', function() {
                //alert( "Load was performed." );
                selectedArr.forEach(function(entry) {
                    console.log(entry);
                    $(":checkbox[value=" + entry + "]").prop('checked', true);
                });
            });
        }

    });
}
