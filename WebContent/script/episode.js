$(document).ready(function () {

    $.ajax({
		url: '/j2eehazi/rest/series',
		type: "GET",
		xhrFields: {
			withCredentials: true
		},
		success: function (data, textStatus, xhr) {
			var seriesSelect = document.getElementById("seriesSelect");
			seriesSelect.options.length = 0;

			var str = JSON.stringify(data);
            var obj = JSON.parse(str);

			for (var i = 0; i < obj.length; i++) {
                seriesSelect.options[i] = new Option(obj[i].name, obj[i].id, false, false);
			}

		},
		error: function (xhr, textStatus) {
			console.log(xhr.status);
		}
	});

    $("#save_button").click(function() {

        var seriesSelect = document.getElementById("seriesSelect");
	    var seriesId = seriesSelect.options[seriesSelect.selectedIndex].value;
	

        var data = {
            name: document.getElementById('ex1').value,
            description: document.getElementById('ex2').value,
            season: document.getElementById('ex3').value,
            episode: document.getElementById('ex4').value,
            sery : {
                id: seriesId
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