<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<h1> Admin Dashboard</h1>
	<h2> Welcome, ${sessionScope.currentuser.userFirstName} ! </h2>
	
	<p>Search for Bids:</p>
	<form action="${contextPath}/v1/bid/search.htm" method="post">
	
		<table>
			<tr>
			    <td>PRODUCT CATEGORY:</td>
			    <td>
					<select name="searchcategory" required="required">
						<option value="electronic">Electronics</option>
						<option value="furniture">Furniture</option>
						<option value="books">Books</option>
						<option value="clothing">Clothing</option>
						<option value="homedecor">Home Decor</option>
						<option value="other">Other</option>	
					</select>
				</td>
			</tr>		
		
			<tr>
			    <td>BID STATUS:</td>
			    <td>
					<select name="searchbidstatus" required="required">
						<option value="open">Open</option>
						<option value="closed">Closed</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="Search" /></td>
			</tr>
		</table>
	</form>
<br><br>
<%@ include file="admin-menu.jsp" %>
</body>
</html>