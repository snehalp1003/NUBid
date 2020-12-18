<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>All Users</h1>
	
	<table border="1">		
		<tr>
			<th>User Email Address</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<c:forEach items="${sessionScope.allusers}" var="adminuserview">
		<tr>
			<td>${adminuserview.userEmail}</td>
			<td>${adminuserview.userFname}</td>
			<td>${adminuserview.userLname}</td>
		</tr>
		</c:forEach>
	</table>
	<br><br>
	
<br><br>  
<%@ include file="admin-menu.jsp" %>
</body>
</html>