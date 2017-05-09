$(document).ready(function () {

    $.ajax({
        url: '/j2eehazi/rest/userFavourites',
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
                button.innerHTML = "Delete";
                button.id = obj[i][0];
                button.name = obj[i][0];
                button.addEventListener('click', function (event) { deleteFavourite(event) });
                button2 = document.createElement("button");
                button2.innerHTML = "Open";
                button2.id = obj[i][0] + "open";
                button2.name = obj[i][0] + "open";
                button2.addEventListener('click', function (event) { openFavourite(event) });
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(butto2);
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


function deleteFavourite(event) {
    $.ajax({
        url: '/j2eehazi/rest/userFavourites/' + event.target.id,
        type: "DELETE",
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

function openFavourite(event) {
    var strId = event.target.id;
    var id = strId.replace("open", "");
    $.ajax({
        url: '/j2eehazi/rest/episodes/' + id,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            var seriesTable = document.getElementById("SeriesTable");
            seriesTable.style.display = "none";

            var episodesTable = document.getElementById("EpisodesTable");
            episodesTable.style.display = "table";

            var tbody = table.getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";

            var obj = JSON.parse(data);

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
                textnode3 = document.createTextNode(obj[i][3] + "/" + obj[i][4]);
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(textnode3);
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
};