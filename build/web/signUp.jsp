<%-- 
    Document   : signUp
    Created on : Jan 20, 2021, 7:33:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <a href="login">login</a>
        <form action="signUp" method="POST">
            Email: <input type="text" name="txtEmailSignUp" value="" placeholder="Email is id"><br/>
            Name: <input type="text" name="txtNameSignUp" value=""><br/>
            Password: <input type="password" name="txtPasswordSignUp" value=""><br/>
            <input type="submit" name="btnAction" value="Sign up">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
