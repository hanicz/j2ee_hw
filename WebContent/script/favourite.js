$(document).ready(function () {

    loadFavouriteTable();

    $("#backButton").click(function() {
        var episodesTable = document.getElementById("EpisodesTable");
        var episodeDiv = document.getElementById("backDiv");
        episodeDiv.style.display = "none";

        var tbody = episodesTable.getElementsByTagName("tbody")[0];
        tbody.innerHTML = "";

        loadFavouriteTable();
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
            loadFavouriteTable();
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });
};

function loadFavouriteTable(){
    $.ajax({
        url: '/j2eehazi/rest/userFavourites',
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            var str = JSON.stringify(data);
            var obj = JSON.parse(str);
            var table = document.getElementById("SeriesTable");
            table.style.display = "table";
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

                textnode1 = document.createTextNode(obj[i].name);
                textnode2 = document.createTextNode(obj[i].description);
                button = document.createElement("button");
                button.innerHTML = "Delete";
                button.id = obj[i].id;
                button.name = obj[i].id;
                button.addEventListener('click', function (event) { deleteFavourite(event) });
                button2 = document.createElement("button");
                button2.innerHTML = "Open";
                button2.id = obj[i].id + "open";
                button2.name = obj[i].id + "open";
                button2.addEventListener('click', function (event) { openFavourite(event) });
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(button2);
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
}

function openFavourite(event) {
    var strId = event.target.id;
    var id = strId.replace("open", "");
    $.ajax({
        url: '/j2eehazi/rest/episodes/series/' + id,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            var seriesTable = document.getElementById("SeriesTable");
            seriesTable.style.display = "none";

            var episodesTable = document.getElementById("EpisodesTable");
            var episodeDiv = document.getElementById("backDiv");
            episodeDiv.style.display = "block";

            var tbody = episodesTable.getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";

            var str = JSON.stringify(data);
            var obj = JSON.parse(str);

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
                textnode1 = document.createTextNode(obj[i].name);
                textnode2 = document.createTextNode(obj[i].description);
                textnode3 = document.createTextNode(obj[i].season + "/" + obj[i].episode);
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