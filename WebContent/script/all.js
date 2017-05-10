$(document).ready(function () {
    loadTable();

    $("#save_button").click(function() {

        var data = {
            name: document.getElementById('ex1').value,
            description: document.getElementById('ex2').value,
            id: document.getElementById('ex3').value
        };

        $.ajax({
            url: '/j2eehazi/rest/series',
            data: JSON.stringify(data),
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (myToken) {
                loadTable();
            },
            error: function () {
                loadTable();
            }
        });
    });
});


function loadTable(){
    $.ajax({
        url: '/j2eehazi/rest/series',
        type: "GET",
        dataType: 'json',
        xhrFields: { withCredentials: true },
        success: function (data, textStatus, xhr) {
            var seriesTable = document.getElementById("SeriesTable");
            seriesTable.style.display = "table";

            var editDiv = document.getElementById("editDiv");
            editDiv.style.display = "none";

            var str = JSON.stringify(data);
            var obj = JSON.parse(str);
            var table = document.getElementById("SeriesTable");
            var tbody = table.getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";

            for (var i = 0; i < obj.length; i++) {
                row = document.createElement("tr");
                cell1 = document.createElement("td");
                cell1.className = "col-md-4";
                cell1.style.textAlign = "center";
                cell2 = document.createElement("td");
                cell2.className = "col-md-3";
                cell2.style.textAlign = "center";
                cell3 = document.createElement("td");
                cell3.className = "col-md-5";
                cell3.style.textAlign = "center";

                textnode1 = document.createTextNode(obj[i].name);
                textnode2 = document.createTextNode(obj[i].description);
                button = document.createElement("button");
                button.innerHTML = "Favourite";
                button.id = obj[i].id;
                button.name = obj[i].id;
                button.addEventListener('click', function (event) { addFavourite(event) });
                button2 = document.createElement("button");
                button2.innerHTML = "Delete";
                button2.id = obj[i].id + 'delete';
                button2.name = obj[i].id + 'delete';
                button2.addEventListener('click', function (event) { deleteSeries(event) });
                button3 = document.createElement("button");
                button3.innerHTML = "Edit";
                button3.id = obj[i].id + 'edit';
                button3.name = obj[i].id + 'edit';
                button3.addEventListener('click', function (event) { editSeries(event) });
                cell1.appendChild(textnode1);
                cell2.appendChild(textnode2);
                cell3.appendChild(button);
                cell3.appendChild(button2);
                cell3.appendChild(button3);
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

function addFavourite(event) {
    $.ajax({
        url: '/j2eehazi/rest/userFavourites/' + event.target.id,
        type: "POST",
        xhrFields: { withCredentials: true },
        success: function (data, textStatus, xhr) {
            console.log(xhr.status);
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });
};

function deleteSeries(event) {

    var strId = event.target.id;
    var id = strId.replace("delete", "");

    $.ajax({
        url: '/j2eehazi/rest/series/' + id,
        type: "DELETE",
        xhrFields: { withCredentials: true },
        success: function (data, textStatus, xhr) {
            loadTable();
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });
};

function editSeries(event) {

    var strId = event.target.id;
    var id = strId.replace("edit", "");

    $.ajax({
        url: '/j2eehazi/rest/series/' + id,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        success: function (data, textStatus, xhr) {
            var seriesTable = document.getElementById("SeriesTable");
            seriesTable.style.display = "none";
            document.getElementById('ex3').style.display = "none"

            var editDiv = document.getElementById("editDiv");
            editDiv.style.display = "block";

            var str = JSON.stringify(data);
            var obj = JSON.parse(str);

            document.getElementById('ex1').value = obj.name;
            document.getElementById('ex2').value = obj.description;
            document.getElementById('ex3').value = obj.id;
            
        },
        error: function (xhr, textStatus) {
            //window.location.replace('/j2eehazi');
        }
    });
};