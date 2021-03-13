<%-- 
    Document   : login
    Created on : Jan 20, 2021, 7:37:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="loginServlet" method="POST">
            Your ID: <input type="text" name="txtEmailLogin" value="2">
            Password: <input type="text" name="txtPasswordLogin" value="2">
            <input type="submit" name="btnAction" value="Login">
            <input type="reset" value="Reset">
            ${sessionScope.MSG_LOGIN}
        </form>
        <a href="signUpPage">Sign Up</a>
    </body>
</html>
