<%-- 
    Document   : addQuestion
    Created on : Jan 22, 2021, 11:11:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question Page</title>
    </head>
    <body>
        <form action="createQuestion" method="POST">
            Id: <input type="text" name="txtIdQuestionToCreate" value="">
            Question content: <input type="text" name="txtContentQuestionToCreate">
            Answer content: <input type="text" name="txtAnswerContentToCreate">
            Subject ID: <input type="text" name="txtSubjectIDToCreate">
            Status: <input type="text" name="txtStatusIDToCreate">
        </form>
        <a href="admin">Back to Admin's Page</a>
    </body>
</html>
