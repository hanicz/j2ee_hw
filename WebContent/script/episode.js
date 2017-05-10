$(document).ready(function () {


    $("#save_button").click(function() {

        var data = {
            name: document.getElementById('ex1').value,
            description: document.getElementById('ex2').value,
            season: document.getElementById('ex3').value,
            episode: document.getElementById('ex4').value,
            sery : {
                id: 100001
            }
        };

        $.ajax({
            url: '/j2eehazi/rest/episodes',
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