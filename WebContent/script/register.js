function register(user, password, pwagain) {
    if (user == "" || password == "" || pwagain == "") {
        document.getElementById('myLabel').innerHTML = 'Please fill the textboxes!';
    } else if (user.indexOf(' ') >= 0) {
        document.getElementById('myLabel').innerHTML = "Username can't contain whitespace characters!";
    } else if (password != pwagain) {
        document.getElementById('myLabel').innerHTML = "The two passwords don't match!";
    } else {

        var registerData = {
            username: user,
            password: password
        };

        $.ajax({
            url: '/j2eehazi/rest/users/register',
            data: JSON.stringify(registerData),
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (myToken) {
                window.location.replace('/j2eehazi');
            },
            error: function () {
                document.getElementById('myLabel').innerHTML = 'Registration failed';
                document.getElementById('ex3').value = "";
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
            register(document.getElementById('ex1').value, document.getElementById('ex2').value, document.getElementById('ex3').value);
        }
    }

    $('#register_button').click(function () {
        register(document.getElementById('ex1').value, document.getElementById('ex2').value, document.getElementById('ex3').value);
    });
});