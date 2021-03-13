<%-- 
    Document   : home
    Created on : Jan 20, 2021, 8:01:02 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <style type="text/css">
            input.answer{
                width: 50px;
            }
        </style>
    </head>
    <body>
        <a href="login">login</a>
        <a href="logOut">Log out</a><br/>
        <a href="showQS">Get all Question</a>
        ${sessionScope.LOGIN_NAME}
        <c:set var="END" value="${requestScope.END}"></c:set> 
        <c:set var="FLAG_PAGING" value="${sessionScope.FLAG_PAGING}"></c:set> 
        <c:set var="searchQuestionBy" value="${sessionScope.searchQuestionBy}"></c:set> 
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>
        <c:set var="result" value="${fn:split('a-b-c-d', '-')}" ></c:set>
        <c:set var="resultSubject" value="${fn:split('Java Desktop-Java Web', '-')}" ></c:set>
        <c:set var="resultTF" value="${fn:split('true-false', '-')}" ></c:set>
        <c:set var="resultSearch" value="${fn:split('Question name-Question status-Question subject', '-')}" ></c:set>
            
            <form action="searchQS" method="POST">
                <input type="text" name="txtSearchQuestion" value="${sessionScope.searchQuestion}">
            Search by:
            <select name="txtSearchQuestionBy">
                <c:forEach var="i" items="${resultSearch}">
                    <c:if test="${i.trim().equals(searchQuestionBy)}">
                        <option  value="${i}" selected="">${i}</option>
                    </c:if>
                    <c:if test="${!i.trim().equals(searchQuestionBy)}">
                        <option  value="${i}" >${i}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" name="btnAction" value="Search question">
        </form>
        <c:set var="list" value="${requestScope.LIST_QUESTION}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table width ="100%" border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Question</th>
                            <th>Answer</th>
                            <th>Create date</th>
                            <th>Subject</th>
                            <th>Status</th>
                            <th>Id</th>
                            <th>Delete</th>
                            <th>Update</th>
                            <th>Update Question</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="editQS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td  class="question">
<!--                                    <input class="question" type="text" name="txtQuestionToUpDate" value="${f.question}">-->
                                    ${f.question}
                                </td>
                                <td>       
                                    <select name="txtAnswerToUpdate">
                                        <c:forEach var="i" items="${result}">
                                            <c:if test="${i.trim().equals(f.answer.trim())}">
                                                <option  value="${i}" selected="">${i}</option>
                                            </c:if>
                                            <c:if test="${!i.trim().equals(f.answer.trim())}">
                                                <option  value="${i}" >${i}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                        <!--<input class="answer" type="text" name="txtAnswerToUpDate" value="${f.answer}">-->
                                </td>
                                <td>   
                                    ${f.createDate}
                                </td>
                                <td>  
                                    <select name="txtSubjectToUpdate">
                                        <c:forEach var="i" items="${resultSubject}">
                                            <c:if test="${i.trim().equals(f.subject.trim())}">
                                                <option  value="${i}" selected="">${i}</option>
                                            </c:if>
                                            <c:if test="${!i.trim().equals(f.subject.trim())}">
                                                <option  value="${i}" >${i}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <!--<input type="text" name="txtSubjectToUpdate" value="${f.subject}">-->
                                </td>
                                <td>   
                                    <select name="txtStatusToUpdate">
                                        <c:forEach var="i" items="${resultTF}">
                                            <c:if test="${i.trim().equals(f.status.trim())}">
                                                <option value="${i}" selected="">${i}</option>
                                            </c:if>
                                            <c:if test="${!i.trim().equals(f.status.trim())}">
                                                <option  value="${i}" >${i}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <!--<input type="text" name="txtStatusToUpDate" value="${f.status}">-->
                                </td>
                                <td>  
                                    ${f.idQuestion}
                                </td>
                                <td>
                                    <input type="hidden" name="txtIdHiddenToDelete" value="${f.idQuestion}">
                                    <input type="submit" name="btnAction" value="Delete This Question"> 
                                </td>
                                <td>
                                    <input type="hidden" name="txtIdHidden" value="${f.idQuestion}">
                                    <input type="submit" name="btnAction" value="Update This Question">
                                </td>
                                <td>
                                    <input type="hidden" name="txtIdHidden" value="${f.idQuestion}">
                                    <input type="submit" name="btnAction" value="Update Question">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
    <c:if test="${FLAG_PAGING.equals('search')}">
        <form action="searchQS" method="POST">
            <input type="hidden" name="txtSearchQuestion" value="${sessionScope.searchQuestion}">
            <input type="hidden" name="txtSearchQuestionBy" value="${searchQuestionBy}">
            <c:forEach var="i" begin="1" end="${END}">
                <input type="submit" name="btnPage" value="${i}">    
            </c:forEach>
        </form>
    </c:if>
    <c:if test="${FLAG_PAGING.equals('showAll')}">
        <form action="showQS" method="POST">
                <c:forEach var="i" begin="1" end="${END}">
                <input type="submit" name="btnPage" value="${i}">    
            </c:forEach>
        </form>
    </c:if>


</body>
</html>
