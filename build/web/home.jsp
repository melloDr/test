<%-- 
    Document   : home
    Created on : Jan 22, 2021, 1:21:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home Page</title>
        <script language="javascript">
            function startTimer(duration, display) {
                var timer = duration, minutes, seconds;
                setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = minutes + ":" + seconds;

                    if (--timer < 0) {
                        timer = duration;
                    }
                }, 1000);
            }

            window.onload = function () {
                var fiveMinutes = 60 * 5,
                        display = document.querySelector('#time');
                startTimer(fiveMinutes, display);
            };
        </script>
    </head>
    <body>
        <div>Your exam has <span id="time"></span> minutes to do!</div>
    </body>
</html>
