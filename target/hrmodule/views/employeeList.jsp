<%--
  Created by IntelliJ IDEA.
  User: akhojayev
  Date: 23-Jul-20
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container">
    <a class="btn btn-success" href="${ctx}/employee/new">Add Emplyee</a>


    <table class="table table-hover">
        <thead>
        <tr>
            <th>Image</th>
            <th>ID</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>HIreDate</th>
            <th>JobID</th>
            <th>Salary</th>
            <th>Commission</th>
            <th>ManagerId</th>
            <th>DepartmentId</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${empList}" var="employee">
            <tr>
                <td> <img src="${ctx}/download/${employee.getImage() }" /></td>
                <td> <c:out value="${employee.getEmployeeId() }"/></td>
                <td> <c:out value="${employee.getFirstName() }"/></td>
                <td> <c:out value="${employee.getLastName() }"/></td>
                <td> <c:out value="${employee.getEmail() }"/></td>
                <td> <c:out value="${employee.getPhoneNumber() }"/></td>
                <td> <c:out value="${employee.getHireDate() }"/></td>
                <td> <c:out value="${employee.getJobId() }"/></td>
                <td> <c:out value="${employee.getSalary() }"/></td>
                <td> <c:out value="${employee.getCommissionPct() }"/></td>
                <td> <c:out value="${employee.getManagerId() }"/></td>
                <td> <c:out value="${employee.getDepartmentId()}"/></td>

                <td>
                    <button class="btn button btn-danger">Delete</button>
                    <a href="${ctx}/employee/edit?employeeId=${employee.getEmployeeId()}" class="btn button btn-warning">Udpdate</a></td>

            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
