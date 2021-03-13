<%-- 
    Document   : showQuestion
    Created on : Jan 29, 2021, 9:25:57 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="aList" class="java.util.ArrayList"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Question Page</title>
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
//                        timer = duration;
                        if (minutes === 0 && seconds === 0) {
                            document.getElementById('submitQuiz').click();
                            return;
                        }
                    }

                }, 1000);

            }

            window.onload = function () {
                var fiveMinutes = ${sessionScope.QUANTITY} * 60,
                        display = document.querySelector('#time');
                startTimer(fiveMinutes, display);
            };
        </script>
    </head>
    <body>
        <div>Your exam has <span id="time"></span> minutes to do!</div>
        <c:set var="SUBJECTTODO" value="${sessionScope.SUBJECTTODO}"></c:set> 
        <c:set var="list" value="${sessionScope.LISTTODO}"></c:set> 
        <c:set var="countt" value="${0}"></c:set>         
        <c:set var="result" value="${fn:split('a-b-c-d', '-')}" ></c:set>
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Question</th>
                            <th>Your answer</th>
                            <th>Save your answer</th>
                        </tr>
                    </thead>
                    <tbody>             
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="takeTestS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.question}</td>
                                <td>
                                    <select name="txtYourAnswer">
                                        <c:forEach var="i" items="${result}">
                                            <c:if test="${i.trim().equals(f.choice)}">
                                                <option  value="${i}" selected="">${i}</option>
                                            </c:if>
                                            <c:if test="${!i.trim().equals(f.choice)}">
                                                <option  value="${i}" >${i}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select> 
                                    <input type="hidden" name="txtIdQuestionHidden" value="${f.idQuestion}"
                                </td>
                                <td>
                                    <input type="hidden" name="txtIdHiddenToSave" value="${f.idQuestion}">
                                    <input type="submit" name="txtSubmit" value="Submit">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>


    <c:if test="${SUBJECTTODO.equals('Java Desktop')}">
        <form action="takeExamS" method="POST">
            <input type="submit" name="txtPaging" value="1">
            <input type="submit" name="txtPaging" value="2">
            <input type="submit" name="txtPaging" value="3">
            <input type="submit" name="txtPaging" value="4">
            <input type="submit" name="txtPaging" value="5">
            <input type="submit" name="txtPaging" value="6">
            <input type="submit" name="txtPaging" value="7">
            <input type="submit" name="txtPaging" value="8">
            <input type="submit" name="txtPaging" value="9">
            <input type="submit" name="txtPaging" value="10">
            <input type="submit" name="txtPaging" value="11">
            <input type="submit" name="txtPaging" value="12">
            <input type="submit" name="txtPaging" value="13">
            <input type="submit" name="txtPaging" value="14">
            <input type="submit" name="txtPaging" value="15">
            <input type="submit" name="txtPaging" value="16">
            <input type="submit" name="txtPaging" value="17">
            <input type="submit" name="txtPaging" value="18">
            <input type="submit" name="txtPaging" value="19">
            <input type="submit" name="txtPaging" value="20">
            <input type="submit" name="txtPaging" value="21">
            <input type="submit" name="txtPaging" value="22">
            <input type="submit" name="txtPaging" value="23">
            <input type="submit" name="txtPaging" value="24">
            <input type="submit" name="txtPaging" value="25">
            <input type="submit" name="txtPaging" value="26">
            <input type="submit" name="txtPaging" value="27">
            <input type="submit" name="txtPaging" value="28">
            <input type="submit" name="txtPaging" value="29">
            <input type="submit" name="txtPaging" value="30">
            <input type="submit" name="txtPaging" value="31">
            <input type="submit" name="txtPaging" value="32">
            <input type="submit" name="txtPaging" value="33">
            <input type="submit" name="txtPaging" value="34">
            <input type="submit" name="txtPaging" value="35">
            <input type="submit" name="txtPaging" value="36">
            <input type="submit" name="txtPaging" value="37">
            <input type="submit" name="txtPaging" value="38">
            <input type="submit" name="txtPaging" value="39">
            <input type="submit" name="txtPaging" value="40">
        </form>
    </c:if> 
    <c:if test="${SUBJECTTODO.equals('Java Web')}">
        <form action="takeExamS" method="POST">
            <input type="submit" name="txtPaging" value="1">
            <input type="submit" name="txtPaging" value="2">
            <input type="submit" name="txtPaging" value="3">
            <input type="submit" name="txtPaging" value="4">
            <input type="submit" name="txtPaging" value="5">
            <input type="submit" name="txtPaging" value="6">
            <input type="submit" name="txtPaging" value="7">
            <input type="submit" name="txtPaging" value="8">
            <input type="submit" name="txtPaging" value="9">
            <input type="submit" name="txtPaging" value="10">
            <input type="submit" name="txtPaging" value="11">
            <input type="submit" name="txtPaging" value="12">
            <input type="submit" name="txtPaging" value="13">
            <input type="submit" name="txtPaging" value="14">
            <input type="submit" name="txtPaging" value="15">
            <input type="submit" name="txtPaging" value="16">
            <input type="submit" name="txtPaging" value="17">
            <input type="submit" name="txtPaging" value="18">
            <input type="submit" name="txtPaging" value="19">
            <input type="submit" name="txtPaging" value="20">
            <input type="submit" name="txtPaging" value="21">
            <input type="submit" name="txtPaging" value="22">
            <input type="submit" name="txtPaging" value="23">
            <input type="submit" name="txtPaging" value="24">
            <input type="submit" name="txtPaging" value="25">
            <input type="submit" name="txtPaging" value="26">
            <input type="submit" name="txtPaging" value="27">
            <input type="submit" name="txtPaging" value="28">
            <input type="submit" name="txtPaging" value="29">
            <input type="submit" name="txtPaging" value="30">
            <input type="submit" name="txtPaging" value="31">
            <input type="submit" name="txtPaging" value="32">
            <input type="submit" name="txtPaging" value="33">
            <input type="submit" name="txtPaging" value="34">
            <input type="submit" name="txtPaging" value="35">
            <input type="submit" name="txtPaging" value="36">
            <input type="submit" name="txtPaging" value="37">
            <input type="submit" name="txtPaging" value="38">
            <input type="submit" name="txtPaging" value="39">
            <input type="submit" name="txtPaging" value="40">
            <input type="submit" name="txtPaging" value="41">
            <input type="submit" name="txtPaging" value="42">
            <input type="submit" name="txtPaging" value="43">
            <input type="submit" name="txtPaging" value="44">
            <input type="submit" name="txtPaging" value="45">
            <input type="submit" name="txtPaging" value="46">
            <input type="submit" name="txtPaging" value="47">
            <input type="submit" name="txtPaging" value="48">
            <input type="submit" name="txtPaging" value="49">
            <input type="submit" name="txtPaging" value="50">
        </form>
    </c:if>
    <form action="submitExamS" method="POST">
        <input type="submit" name="btnSubmitExam" value="SubmitExam" id="submitQuiz">
    </form>
</body>
</html>
