<%-- 
    Document   : resultExam
    Created on : Feb 1, 2021, 1:26:29 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <a href="logOut">Log out</a><br/>
        <c:set var="resultSearch" value="${fn:split('Java Desktop-Java Web', '-')}" ></c:set>      
        <c:set var="SSSEARCHHISTORY" value="${sessionScope.SSSEARCHHISTORY}"></c:set> 
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>
        Hello: ${sessionScope.LOGIN_NAME};<br/>
        Your exam of ${sessionScope.SUBJECTTODO}<br/>
        You do exam from ${sessionScope.STARTTIME}<br/>
        Sum of your correct answers are: ${sessionScope.SUMCORRECT}<br/>
        And your point is: ${sessionScope.POINT}<br/>

        <form action="searchHistoryS" method="POST">
            Search by:
            <select name="txtSearchHistory">
                <c:forEach var="i" items="${resultSearch}">
                    <c:if test="${i.trim().equals(SSSEARCHHISTORY)}">
                        <option  value="${i}" selected="">${i}</option>
                    </c:if>
                    <c:if test="${!i.trim().equals(SSSEARCHHISTORY)}">
                        <option  value="${i}" >${i}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="Search history">
        </form>
        <c:set var="list" value="${sessionScope.LIST_HISTORY}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Id history</th>
                            <th>Id subject</th>
                            <th>Name</th>
                            <th>Start time</th>
                            <th>End time</th>
                            <th>Number of correct</th>
                            <th>Point</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.idHistory}</td>
                                <td>${f.idSubject}</td>
                                <td>${f.id}</td>
                                <td>${f.startTime}</td>
                                <td>${f.endTime}</td>
                                <td>${f.sumCorrect}</td>
                                <td>${f.point}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
        <c:if test="${END } >= 20">
            <form action="searchHistoryS">
                <c:forEach var="i" begin="1" end="${END}">
                    <input type="submit" name="btnPage" value="${i}">    
                </c:forEach>
            </form>
        </c:if>

    </body>
</html>
