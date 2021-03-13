<%-- 
    Document   : updateContent
    Created on : Jan 26, 2021, 10:48:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Content Page</title>
    </head>
    <body>
        <a href="showQS">Get all Question</a>
        <c:set var="CONTENT_QUESTION" value="${requestScope.CONTENT_QUESTION}"></c:set>
        <c:set var="ID_HIDDEN" value="${requestScope.ID_HIDDEN}"></c:set>
            <form action="updateContentS" method="POST">
            ${CONTENT_QUESTION}<br/>
            <h2>You can update your question here, going back to the line using the &lt;br&gt; tag</h2><br/>

            <textarea name="txtContentToUpdate" rows="9" cols="70">  
${CONTENT_QUESTION}
            </textarea>
            <input type="hidden" name="txtIdHiddenToUpdate" value="${ID_HIDDEN}">
            <input type="submit" name="btnAction" value="Modify">
        </form>
    </body>
</html>
