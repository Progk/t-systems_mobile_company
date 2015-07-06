/*
$(document).ready(function() {
    $("#login_form").on('submit',function(){
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : $(this).attr('action'),
            data     : $("#login_form").serialize(), //keeps all input data
            success  : function(data) {
                alert(data.text());
            },
            error    :function(data) {
                alert(data.text());
            }
        });
    });


});
*/

jQuery(document).ready(function ($) {
    $('#tabs').tab();
});

