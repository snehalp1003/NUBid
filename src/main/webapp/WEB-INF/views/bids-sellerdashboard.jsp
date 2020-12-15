<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Products</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1>Bids placed for Product, ${sessionScope.prodName}</h1>
	
	<table border="1">		
		<tr>
			<th>Bidder Username</th>
			<th>Bid Price</th>
			<th>Accept Offer</th>
		</tr>
		<c:forEach items="${sessionScope.bidsPlaced}" var="bid">
		<tr>
			<td>${bid.key}</td>
			<td>${bid.value}</td>
			<td>
			<form action="${contextPath}/v1/bid/accept.htm" method="POST">
					<input type="hidden" name="acceptedBidderEmail" value="${bid.key}"/>
					<input type="hidden" name="acceptedOfferPrice" value="${bid.value}"/>
					<input type="hidden" name="acceptedProdId" value="${sessionScope.prodId}"/>
					<input type=submit value="Accept Bid Offer">
			</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<br><br>
	
<br><br>
<%@ include file="user-menu.jsp" %>
</body>
</html>