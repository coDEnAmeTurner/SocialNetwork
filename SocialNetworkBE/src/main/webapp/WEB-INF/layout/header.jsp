<%-- 
    Document   : header
    Created on : Jun 12, 2024, 4:09:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>">Social Network Administration</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/users/" />">User</a>
                </li>

                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class=" btn btn-info " href="<c:url value="/login" />">Log in</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class=" btn btn-danger " href="<c:url value="/" />">Hello ${pageContext.request.userPrincipal.name}!</a>
                        </li>
                        <li class="nav-item">
                            <a class=" btn btn-info ms-2" href="<c:url value="/logout" />">Log out</a>
                        </li>
                    </c:when>
                </c:choose>
                <li class="nav-item">
                    <a class=" btn btn-danger ms-2" href="<c:url value="/statistics/" />">Statistics</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
