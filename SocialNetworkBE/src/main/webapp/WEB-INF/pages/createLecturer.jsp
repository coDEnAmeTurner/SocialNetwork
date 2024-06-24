<%-- 
    Document   : createLecturer
    Created on : Jun 14, 2024, 7:44:21 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text text-bg-primary text-center">CREATE LECTURER</h1>

<c:url value="/users/create-lecturer/" var="action" />
<form:form class="ms-4 me-4" method="post" action="${action}" modelAttribute="formLecturer" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <c:if var="error" test="${createError != null}">
        <div class="alert alert-danger">
            <strong>Danger!</strong> ${createError}
        </div>


    </c:if>
    <c:if var="error" test="${usernameError != null}">
        <div class="alert alert-danger">
            <strong>Danger!</strong> ${usernameError}
        </div>


    </c:if>
    <c:if var="error" test="${emailError != null}">
        <div class="alert alert-danger">
            <strong>Danger!</strong> ${emailError}
        </div>


    </c:if>


    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="fullName"  placeholder="Enter full name" path="fullName" />
        <label for="fullName">Full Name</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control"  id="image" path="file" />
        <label for="image">Choose your avatar: </label>

        <c:if test="${product.id > 0}">
            <img src="${product.image}" width="200" class="img-fluid" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="username"  placeholder="Enter username" path="username" />
        <label for="username">Username:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="dob" path="dob" />
        <label for="dob">Date Of Birth: </label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="email" path="email" />
        <label for="email">Email: </label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="phone" path="phone" />
        <label for="phone">Phone </label>
    </div>    

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="degreeId"  path="degreeId">
            <c:forEach items="${degrees}" var="d">
                <option value="${d.id}" >${d.degreeName}</option>
            </c:forEach>
        </form:select>
        <label for="categoryId" class="form-label">Degree:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="academicRankId"  path="academicRankId">
            <c:forEach items="${academicRanks}" var="ar">
                <option value="${ar.id}" >${ar.academicRankName}</option>
            </c:forEach>
            <option value="" >No Rank</option>
        </form:select>
        <label for="categoryId" class="form-label">Academic Rank: </label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="titleId"  path="titleId">
            <c:forEach items="${titles}" var="t">
                <option value="${t.id}" >${t.titleName}</option>
            </c:forEach>
        </form:select>
        <label for="titleId" class="form-label">Title: </label>
    </div>

    <div >
        <button class="btn btn-info mt-1" type="submit">
            Create
        </button>
        <form:hidden path="id" />
    </div>
</form:form>