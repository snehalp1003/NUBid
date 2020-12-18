<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Closed Bids</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Closed Bids</h1>
	
	<table border="1">		
		<tr>
			<th>Seller Email Address</th>
			<th>Product Name</th>
			<th>Product Category</th>
			<th>Minimum Bid Price</th>
			<th>Bid Start Date</th>
			<th>Buyer Email Address</th>
			<th>Bid Selling Price</th>
			<th>Bid End Date</th>
		</tr>
		<c:forEach items="${sessionScope.allclosedbids}" var="closedbids">
		<tr>
			<td>${closedbids.sellerEmail}</td>
			<td>${closedbids.prodName}</td>
			<td>${closedbids.prodCategory}</td>
			<td>${closedbids.bidStartPrice}</td>
			<td>${closedbids.bidStartDate}</td>
			<td>${closedbids.buyerEmail}</td>
			<td>${closedbids.bidFinalPrice}</td>
			<td>${closedbids.bidEndDate}</td>			
		</tr>
		</c:forEach>
	</table>
	<br><br>
	
<br><br>  
<%@ include file="admin-menu.jsp" %>
</body>
</html>