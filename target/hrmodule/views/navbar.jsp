<%--
  Created by IntelliJ IDEA.
  User: akhojayev
  Date: 24-Jul-20
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="ctx" value="${pageContext.request.contextPath}"  />

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="${ctx}/employee/list">Employees</a></li>
            <li><a href="#">Jobs</a></li>
            <li><a href="#">Departments</a></li>
            <li><a href="${ctx}/logoff">logoff</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h3>Inverted Navbar</h3>
    <p>An inverted navbar is black instead of gray.</p>
</div>

</body>
</html>
