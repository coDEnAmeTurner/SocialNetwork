<%-- 
    Document   : user
    Created on : Jun 13, 2024, 9:21:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text text-bg-primary text-center">USER ADMINISTRATION HOMEPAGE</h1>
<a href="<c:url value="/users/create-lecturer/" />"  class="btn btn-outline-primary m-2">Create Lecturer</a>
<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Full Name</th>
            <th>Avatar</th>
            <th>Username</th>
            <th>User's Role</th>
            <th>Date Of Birth</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Degree</th>
            <th>Academic Rank</th>
            <th>Student Id</th>
            <th>Locked?</th>
            <th>Dispatching Admin</th>
            <th>Title</th>
            <th></th>
        </tr>
        <c:forEach items="${queriedAlumni}" var="qa">
            <tr>
                <td>${qa[0]}</td>
                <td>${qa[1]}</td>
                <td> <img class="card-img-top" src="${qa[2]}" style="width:200px;"></td>
                <td>${qa[3]}</td>
                <td>${qa[5]}</td>
                <td>${qa[6]}</td>
                <td>${qa[7]}</td>
                <td>${qa[8]}</td>
                <td>${qa[10]}</td>
                <td>${qa[12]}</td>
                <td>${qa[13]}</td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <c:url value="/api/users/${qa[0]}" var="url" />
                    <a class="btn btn-info" href="${url}" />Update</a>
                    <button onclick="" class="btn btn-danger mt-1">Delete</button>
                </td>
            </tr>
        </c:forEach>
        <c:forEach items="${queriedLecturers}" var="ql">
            <tr>
                <td>${ql[0]}</td>
                <td>${ql[1]}</td>
                <td> <img class="card-img-top" src="${ql[2]}" style="width:200px;"></td>
                <td>${ql[3]}</td>
                <td>${ql[5]}</td>
                <td>${ql[6]}</td>
                <td>${ql[7]}</td>
                <td>${ql[8]}</td>
                <td>${ql[10]}</td>
                <td>${ql[12]}</td>
                <td></td>
                <td>${ql[13]}</td>
                <td>${ql[14]}</td>
                <td>${ql[15]}</td>
                <td>
                    <c:url value="/api/users/${qa[0]}" var="url" />
                    <a class="btn btn-info" href="${url}" />Update</a>
                    <button onclick="" class="btn btn-danger mt-1">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
