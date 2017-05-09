function login(user, password) {
    if (user == "" || password == "") {
        document.getElementById('myLabel').innerHTML = 'Please fill the textboxes!';
    }
    else {

        var loginData = {
            username: user,
            password: password
        };

        $.ajax({
            url: '/j2eehazi/rest/users/login',
            data: loginData,
            type: "GET",
            xhrFields: { withCredentials: true },
            success: function (myToken) {
                window.location.replace('/j2eehazi/all.html');
            },
            error: function () {
                document.getElementById('myLabel').innerHTML = 'Error while trying to login';
                document.getElementById('ex2').value = "";
                document.getElementById('ex1').value = "";
            }
        });
    }
};

$(function () {
    window.onkeyup = function (e) {
        var key = e.keyCode ? e.keyCode : e.which;
        if (key == 13) {
            login(document.getElementById('ex1').value, document.getElementById('ex2').value);
        }
    }

    $('#login_button').click(function () {
        login(document.getElementById('ex1').value, document.getElementById('ex2').value);
    });
});