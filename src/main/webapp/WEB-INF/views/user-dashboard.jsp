<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Dashboard</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1> User Dashboard</h1>
	<h2> Welcome, ${sessionScope.currentuser.userFirstName} ! </h2>
	
	<p>Search for Products:</p>
	<form action="${contextPath}/v1/product/search.htm" method="post">
	
		<select name="category" required="required">
			    	<option value="electronic">Electronics</option>
			    	<option value="furniture">Furniture</option>
			    	<option value="books">Books</option>
			    	<option value="clothing">Clothing</option>
			    	<option value="homedecor">Home Decor</option>
			    	<option value="other">Other</option>	
		</select>
		
		<br> <br>
		<input type="submit" name="submit" value="Search" />
	</form>
<br><br>
<%@ include file="user-menu.jsp" %>
</body>
</html>