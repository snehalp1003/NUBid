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
	<h1>Purchase Products</h1>
	
	<table border="1">		
		<tr>
			<th>Product Name</th>
			<th>Category</th>
			<th>Minimum Bid Price</th>
			<th>View Bids</th>
			<th>Place Bid</th>
		</tr>
		<c:forEach items="${sessionScope.productsForPurchase}" var="product">
		<tr>
			<td>${product.prodName}</td>
			<td>${product.prodCategory}</td>
			<td>${product.prodMinPrice}</td>
			<td><a href="${contextPath}/v1/bids/view.htm?prodId=${product.prodId}">View Bids</a></td>
			<td>
				<form action="${contextPath}/v1/bid/offer.htm" method="POST">
					<input type="text" name="newBidPrice" pattern="[0-9]+(\\.[0-9][0-9]?)?{10}" />
					<input type="hidden" name="prodId" value="${product.prodId}"/>
					<input type=submit value="Place Bid">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<br><br>
	
<br><br>
<a href="${contextPath}/v1/product/create.htm">Sell a new Product</a>    
<%@ include file="user-menu.jsp" %>
</body>
</html>