<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="<c:url value="/resources/js/script.js" />"></script>

<h1 class="text text-center text-success">STATISTICS</h1>

<h3 class="text text-primary m-2">SURVEY REPORT </h3>
<div class="form-floating m-2">
    <select id="surveyId" class="form-select">
        <c:forEach items="${surveys}" var="s">
            <option value="${s.id}" >${s.id}. ${s.title}</option>
        </c:forEach>
    </select>
    <label for="surveyId" class="form-label">Choose a survey: </label>
    <button class="btn btn-primary mt-1" onclick="generateSurveyStats()">Generate</button>
</div>

<div class="container" id="surveyChartDiv"></div>

<h3 class="text text-primary m-2">USER REPORT: </h3>
<div id="selection-list" class="d-flex justify-content-around">
    <div id="year-container">
        <input onclick="enableReport('year')" type="radio" class="form-check-input" id="radio1" name="optradio" value="option1">YEAR
        <div class="d-flex justify-content-between m-1">
            <div class="form-floating m-1">
                <input type="number" class="form-control" id="start-year" disabled>
                <label for="start-year" >Start Year: </label>

            </div>
            <div class="form-floating m-1">
                <input type="number" class="form-control" id="end-year" disabled>
                <label for="end-year">End Year: </label>
            </div>
        </div>
        <button disabled class="btn btn-primary m-1" onclick="generateUserStats('year')" id="button-year">Generate</button>

    </div>
    <div id="month-container">
        <input onclick="enableReport('month')" type="radio" class="form-check-input" id="radio2" name="optradio" value="option2">MONTH
        <div class="form-floating m-1">
            <input disabled type="number" class="form-control" id="myear">
            <label for="myear">Months Of Year: </label>
            <button disabled class="btn btn-primary m-1" onclick="generateUserStats('month')" id="button-month">Generate</button>

        </div>
    </div>
    <div id="quarter-container">
        <input onclick="enableReport('quarter')" type="radio" class="form-check-input" id="radio3" name="optradio" value="option3">QUARTER
        <div class="form-floating m-1">
            <input disabled type="number" class="form-control" id="qyear">
            <label for="qyear">Quarters Of Year: </label>
            <button disabled class="btn btn-primary m-1" onclick="generateUserStats('quarter')" id="button-quarter">Generate</button>

        </div>
    </div>
</div>

<h3 class="text text-primary m-2">POST REPORT: </h3>
<div id="selection-list" class="d-flex justify-content-around">
    <div id="year-container">
        <input onclick="enablePostReport('year')" type="radio" class="form-check-input" id="radio1" name="optradio1" value="option1">YEAR
        <div class="d-flex justify-content-between m-1">
            <div class="form-floating m-1">
                <input type="number" class="form-control" id="start-post-year" disabled>
                <label for="start-post-year" >Start Year: </label>

            </div>
            <div class="form-floating m-1">
                <input type="number" class="form-control" id="end-post-year" disabled>
                <label for="end-post-year">End Year: </label>
            </div>
        </div>
        <button disabled class="btn btn-primary m-1" onclick="generatePostStats('year')" id="button-post-year">Generate</button>

    </div>
    <div id="month-container">
        <input onclick="enablePostReport('month')" type="radio" class="form-check-input" id="radio2" name="optradio1" value="option2">MONTH
        <div class="form-floating m-1">
            <input disabled type="number" class="form-control" id="post-myear">
            <label for="post-myear">Months Of Year: </label>
            <button disabled class="btn btn-primary m-1" onclick="generatePostStats('month')" id="button-post-month">Generate</button>

        </div>
    </div>
    <div id="quarter-container">
        <input onclick="enablePostReport('quarter')" type="radio" class="form-check-input" id="radio3" name="optradio1" value="option3">QUARTER
        <div class="form-floating m-1">
            <input disabled type="number" class="form-control" id="post-qyear">
            <label for="post-qyear">Quarters Of Year: </label>
            <button disabled class="btn btn-primary m-1" onclick="generatePostStats('quarter')" id="button-post-quarter">Generate</button>

        </div>
    </div>
</div>

<div class="container" id="postChartDiv"></div>
