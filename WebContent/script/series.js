$(document).ready(function () {


    $("#save_button").click(function() {

        var data = {
            name: document.getElementById('ex1').value,
            description: document.getElementById('ex2').value
        };

        $.ajax({
            url: '/j2eehazi/rest/series',
            data: JSON.stringify(data),
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (myToken) {
                console.log('asd');
            },
            error: function () {
               console.log('asd');
            }
        });
    });

});