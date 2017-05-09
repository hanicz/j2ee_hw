$(document).ready(function () {

    $.ajax({
        url: '/j2eehazi/rest/series',
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            var obj = JSON.parse(data);
            var table = document.getElementById("SeriesTable");
            var tbody = table.getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";

            for (var i = 0; i < obj.length; i++) {
                row = document.createElement("tr");
                cell1 = document.createElement("td");
                cell1.className = "col-md-5";
                cell1.style.textAlign = "center";
                cell2 = document.createElement("td");
                cell2.className = "col-md-3";
                cell2.style.textAlign = "center";
                cell3 = document.createElement("td");
                cell3.className = "col-md-3";
                cell3.style.textAlign = "center";

                textnode1 = document.createTextNode(obj[i][1]);
                textnode2 = document.createTextNode(obj[i][2]);
                button = document.createElement("button");
                button.innerHTML = "Favourite";
                button.id = obj[i][0];
                button.name = obj[i][0];
                button.addEventListener('click', function (event) { addFavourite(event) });
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(button);
                row.appendChild(cell1);
                row.appendChild(cell2);
                row.appendChild(cell3);
                tbody.appendChild(row);
            }
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });

});


function addFavourite(event) {
    $.ajax({
        url: '/j2eehazi/rest/userFavourites/' + event.target.id,
        type: "POST",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            console.log(xhr.status);
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });
};