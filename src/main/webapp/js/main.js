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
                $('#content').load('user.jsp #content', function () {
                    //alert( "Load was performed." );
                    $.each(result, function (key, value) {
                        console.log(value);
                        $(":checkbox[value=" + value + "]").prop('checked', true);
                    });
                });
            }
        });
    });


    $("#newClientForm").submit(function () {
        console.log("newClientFormSubmit");
        var sendData = $("#newClientForm").serializeArray()
        var selectedOptionsArr = [];
        $("input:checkbox[name=optionNewClientPlan]:checked").each(function () {
            selectedOptionsArr.push($(this).val());
        });
        var selectedPlanName = $("#selectPlanNewUser option:selected").text();
        sendData.push({name: 'options', value: selectedOptionsArr});
        sendData.push({name: 'plan', value: selectedPlanName});
        console.log(sendData);
        $.ajax({
            type: "POST",
            url: "/AdminUpdateServlet",
            data: sendData,
            success: function (data) {
                console.log("success");
            }
        });
        return false; // avoid to execute the actual submit of the form.
    });


    $("#editClientForm").submit(function () {
        console.log("editClientFormSubmit");
        var sendData = $("#newClientForm").serializeArray()
        var selectedOptionsArr = [];
        $("input:checkbox[name=optionEditClientPlan]:checked").each(function () {
            selectedOptionsArr.push($(this).val());
        });
        var selectedPlanName = $("#selectPlanEditUser option:selected").text();
        sendData.push({name: 'options', value: selectedOptionsArr});
        sendData.push({name: 'plan', value: selectedPlanName});
        console.log(sendData);
        $.ajax({
            type: "POST",
            url: "/AdminUpdateServlet",
            data: sendData,
            success: function (data) {
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
    $("input:checkbox[name=optionNewPlan]:checked").each(function () {
        selectedArr.push($(this).val());
    });
    var sendData = {type: "selectedOptionArrNewClient", selectedOptionArr: selectedArr}
    $.ajax({
        url: "/AdminUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('.selectNewClientOptionWrapper').load('admin.jsp #selectNewClientOption', function () {
                //alert( "Load was performed." );
                selectedArr.forEach(function (entry) {
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
    $("input:checkbox[name=optionNewClientPlan]:checked").each(function () {
        selectedArr.push($(this).val());
    });
    var sendData = {type: "selectOptionClient", selectedOptionArr: selectedArr}
    $.ajax({
        url: "/UserUpdateServlet",
        method: "POST",
        data: sendData,
        async: false,
        success: function (result) {
            $('.selectOptionWrapper').load('user.jsp #selectOption', function () {
                //alert( "Load was performed." );
                selectedArr.forEach(function (entry) {
                    console.log(entry);
                    $(":checkbox[value=" + entry + "]").prop('checked', true);
                });
            });
        }

    });
}


function exitUser() {
    var sendData = {type: "exitSimpleUser"};

    $.ajax({
        type: "POST",
        url: "/UserUpdateServlet",
        data: sendData,
        dataType: "json",
        success: function (data, textStatus) {
            if (data) {
                // data.redirect contains the string URL to redirect to
                window.location.href = data.redirect;
            }
            else {
                // data.form contains the HTML for the replacement form
                //$("#myform").replaceWith(data.form);
            }
        }
    })
}

function allUserShowContract(data) {
    var sendData = {type: "allUserShowContract", click: data.id}
    $.ajax({
        type: "POST",
        url: "/AdminUpdateServlet",
        data: sendData,
        dataType: "json",
        success: function (data, textStatus) {

        }
    })
    $('.modalShowContractWrapper').load('admin.jsp #modalShowContract');
}


function searchClient(v) {
    var inputNumber = $('#inputNumber').val();
    var sendData = {type: "searchAdminUser", inputNumber: inputNumber}
    $.ajax({
        type: "POST",
        url: "/AdminUpdateServlet",
        data: sendData,
        dataType: "json",
        success: function (data, textStatus) {

        }
    })
    $('.editUserWrapper').load('admin.jsp #editClientForm');
    //$('.modalShowContractWrapper').load('admin.jsp #modalShowContract');
    /*var sendData = $("#editClientForm").serializeArray()
    var selectedOptionsArr = [];
    $("input:checkbox[name=optionNewClientPlan]:checked").each(function () {
        selectedOptionsArr.push($(this).val());
    });
    //var selectedPlanName = $("#selectPlanNewUser option:selected").text();
    sendData.push({name: 'options', value: selectedOptionsArr});
    var sendData = {type: "searchClient", click: data.id}
    $.ajax({
        type: "POST",
        url: "/AdminUpdateServlet",
        data: sendData,
        dataType: "json",
        success: function (data, textStatus) {

        }
    })*/

}