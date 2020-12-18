<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Image</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Image for Product</h1>
	
	<div>
	<img src ="file:///${sessionScope.prodImg}" width="700" height="500"/>
	</div>
	<br><br>
	
<br><br>
<%@ include file="user-menu.jsp" %>
</body>
</html>