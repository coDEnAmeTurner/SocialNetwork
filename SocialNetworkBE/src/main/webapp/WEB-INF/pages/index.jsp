<%-- 
    Document   : index
    Created on : May 23, 2024, 9:26:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text text-center text-primary">WELCOME BACK ${pageContext.request.userPrincipal.name} !!!</h1>
<div class="d-flex justify-content-around">
    <a class="btn btn-outline-info" href="<c:url value="/users/"/>">User</a>
    <a class="btn btn-outline-info" href="<c:url value="/statistics/"/>">Statistics</a>
</div>
