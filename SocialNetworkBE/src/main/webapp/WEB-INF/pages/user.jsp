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
            <th>Approved?</th>
            <th>Locked?</th>
            <th>Dispatching Admin</th>
            <th>Title</th>

        </tr>
        <c:forEach items="${queriedAlumni}" var="qa">
            <tr>
                <td>${qa[2]}</td>
                <td>${qa[3]}</td>
                <td> <img class="card-img-top" src="${qa[4]}" style="width:200px;"></td>
                <td>${qa[5]}</td>
                <td>${qa[1]}</td>
                <td>${qa[6]}</td>
                <td>${qa[7]}</td>
                <td>${qa[8]}</td>
                <td>${qa[10]}</td>
                <td>${qa[12]}</td>
                <td>${qa[13]}</td>
                <td>
                    <c:if test="${qa[14] == true}">
                        Approved!
                    </c:if>
                    <c:if test="${qa[14] == false}">
                        Disapproved!
                        <a type="button" class="btn btn-outline-danger" onclick="approveAlumnus('<c:url value="/api/users/${qa[2]}/approve-alumnus/"/>')">Approve!</a>

                    </c:if>
                </td>
                <td></td>
                <td></td>                
                <td></td>
            </tr>
        </c:forEach>

        <c:forEach items="${queriedLecturers}" var="ql">
            <tr>
                <td>${ql[3]}</td>
                <td>${ql[4]}</td>
                <td><img class="card-img-top" src="${ql[5]}" style="width:200px;"></td>
                <td>${ql[6]}</td>
                <td>${ql[2]}</td>
                <td>${ql[7]}</td>
                <td>${ql[8]}</td>
                <td>${ql[9]}</td>
                <td>${ql[11]}</td>
                <td>${ql[13]}</td>
                <td></td>
                <td></td>
                <td>
                    <c:if test="${ql[14] == true}">
                        Locked!
                        <a type="button" class="btn btn-outline-danger" onclick="unlockLecturer('<c:url value="/api/users/${ql[3]}/unlock-lecturer/"/>')"/>">Unlock!</a>
                    </c:if>
                    <c:if test="${ql[14] == false}">
                        Unlocked!
                    </c:if>
                </td>
                <td>${ql[15]}</td>
                <td>${ql[0]}</td>

            </tr>
        </c:forEach>
            
    </table>
</div>

<script>
    const unlockLecturer = (url) => {
        fetch(url, {method: 'PATCH'}).then(res => {
            if (res.status === 200)
                location.reload();
            else
                alert("Unlocking failed!");

        });
    }
    
    const approveAlumnus = (url) => {
        fetch(url, {method: 'PATCH'}).then(res => {
            if (res.status === 200)
                location.reload();
            else
                alert("Approval failed!");

        });
    }

</script>
