<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Page</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2>Bid Offer Placed Successfully!</h2>

<br><br>
<%@ include file="user-menu.jsp" %>

</body>
</html>