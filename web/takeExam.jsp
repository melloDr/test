<%-- 
    Document   : takeExam
    Created on : Jan 28, 2021, 2:38:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
    </head>
    <body>
        <a href="login">login</a>
        <a href="logOut">Log out</a><br/>
        <form action="takeExamS" method="POST">
            <input type="submit" name="btnAction" value="Java Desktop">
            <input type="submit" name="btnAction" value="Java Web">
        </form>
    <c:set var="FLAGTIMER" scope="session" value="yes"></c:set>
</body>
</html>
